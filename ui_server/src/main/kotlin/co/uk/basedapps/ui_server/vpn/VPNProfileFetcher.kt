package co.uk.basedapps.ui_server.vpn

import android.util.Base64
import arrow.core.Either
import arrow.core.flatMap
import co.sentinel.cosmos.WalletRepository
import co.uk.basedapps.domain.models.KeyPair
import co.uk.basedapps.domain_wireguard.core.repo.WireguardRepository
import co.uk.basedapps.ui_server.network.model.Credentials
import co.uk.basedapps.ui_server.network.model.Protocol
import co.uk.basedapps.ui_server.network.model.StartSessionRequest
import co.uk.basedapps.ui_server.network.repository.BasedRepository
import co.uk.basedapps.ui_server.server.models.CredentialsRequest
import java.nio.ByteBuffer
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VPNProfileFetcher @Inject constructor(
  private val restRepository: BasedRepository,
  private val walletRepository: WalletRepository,
  private val wgRepository: WireguardRepository,
) {

  suspend fun fetch(
    request: CredentialsRequest,
  ): Either<Unit, Credentials> {
    val protocol = Protocol.fromString(request.nodeProtocol)
    if (protocol == Protocol.NONE) {
      return Either.Left(Unit)
    }
    val url = "${request.url}/accounts/${request.address}/sessions/${request.session}"

    return when (protocol) {
      Protocol.WIREGUARD -> getWireguardCredentials(url, request.session)
      Protocol.V2RAY -> connectV2Ray(url, request.session)
      else -> return Either.Left(Unit)
    }
  }

  private suspend fun getWireguardCredentials(
    url: String,
    session: Long,
  ): Either<Unit, Credentials> {
    val keyPair = wgRepository.generateKeyPair()
    return walletRepository.getSignature(session)
      .flatMap { signature ->
        fetchProfile(
          url = url,
          keyPair = keyPair,
          signature = signature,
        )
      }
      .map { profile ->
        Credentials(
          protocol = Protocol.WIREGUARD,
          payload = profile.result,
          privateKey = keyPair.privateKey,
        )
      }
      .mapLeft { }
  }

  private suspend fun connectV2Ray(
    url: String,
    session: Long,
  ): Either<Unit, Credentials> {
    val keyPair = generateKeyPair()
    return walletRepository.getSignature(session)
      .flatMap { signature ->
        fetchProfile(
          url = url,
          keyPair = keyPair,
          signature = signature,
        )
      }
      .map { profile ->
        Credentials(
          protocol = Protocol.V2RAY,
          payload = profile.result,
          privateKey = keyPair.privateKey,
        )
      }
      .mapLeft { }
  }

  private suspend fun fetchProfile(
    url: String,
    keyPair: KeyPair,
    signature: String,
  ) = restRepository.connect(
    url = url,
    body = StartSessionRequest(
      key = keyPair.publicKey,
      signature = signature,
    ),
  )

  private fun generateKeyPair(): KeyPair {
    val uuid = UUID.randomUUID()
    val uuidBytes = asBytes(uuid)
    val privateKey = uuid.toString()
    val clientKeyBytes = byteArrayOf(0x01) + uuidBytes!!
    val publicKey = Base64.encodeToString(clientKeyBytes, Base64.DEFAULT).trim()
    return KeyPair(
      publicKey = publicKey,
      privateKey = privateKey,
    )
  }

  private fun asBytes(uuid: UUID): ByteArray? =
    ByteBuffer.wrap(ByteArray(16))
      .putLong(uuid.mostSignificantBits)
      .putLong(uuid.leastSignificantBits)
      .array()
}
