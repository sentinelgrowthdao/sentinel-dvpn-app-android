package co.uk.basedapps.vpn.vpn

import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.functional.getOrNull
import co.uk.basedapps.domain.functional.requireRight
import co.uk.basedapps.domain.models.VpnTunnel
import co.uk.basedapps.domain_v2ray.V2RayRepository
import co.uk.basedapps.domain_v2ray.model.V2RayVpnProfile
import co.uk.basedapps.domain_wireguard.core.init.DefaultTunnelName
import co.uk.basedapps.domain_wireguard.core.model.WireguardVpnProfile
import co.uk.basedapps.domain_wireguard.core.repo.WireguardRepository
import co.uk.basedapps.vpn.common.BaseError
import co.uk.basedapps.vpn.network.model.Credentials
import co.uk.basedapps.vpn.network.model.Protocol
import co.uk.basedapps.vpn.network.repository.BasedRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

@Singleton
class VPNConnector @Inject constructor(
  private val repository: BasedRepository,
  private val wireguardRepository: WireguardRepository,
  private val v2RayRepository: V2RayRepository,
) {

  val permissionFlow = MutableSharedFlow<PermissionStatus>(
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
  )

  suspend fun connect(credentials: Credentials): Either<Error, Unit> {
    return withContext(Dispatchers.IO) {
      permissionFlow.emit(PermissionStatus.Requested)
      val response = permissionFlow.first { it != PermissionStatus.Requested }
      if (response == PermissionStatus.Allowed) {
        getVPNProfile(
          serverId = credentials.payload,
          credentials = credentials,
        )
      } else {
        Either.Left(Error.RequestPermissions)
      }
    }
  }

  suspend fun disconnect() {
    return withContext(Dispatchers.Main) {
      when {
        wireguardRepository.isConnected() -> disconnectWireguard()
        v2RayRepository.isConnected() -> disconnectV2Ray()
      }
    }
  }

  suspend fun isConnected(): Boolean {
    return wireguardRepository.isConnected() || v2RayRepository.isConnected()
  }

  private suspend fun getVPNProfile(
    serverId: String,
    credentials: Credentials,
  ): Either<Error, Unit> {
    return when (credentials.protocol) {
      Protocol.WIREGUARD -> connectWireguard(
        serverId = serverId,
        privateKey = credentials.privateKey,
        profile = decodeWireguardVpnProfile(credentials.payload),
      )

      Protocol.V2RAY -> connectV2Ray(
        serverId = serverId,
        profile = decodeV2RayVpnProfile(
          payload = credentials.payload,
          uid = credentials.privateKey,
        ),
      )

      else -> throw Exception("Unknown protocol")
    }
  }

  private suspend fun connectWireguard(
    serverId: String,
    privateKey: String,
    profile: WireguardVpnProfile?,
  ): Either<Error, Unit> {
    profile ?: return Either.Left(Error.ParseProfile)
    val keyPair = wireguardRepository.generateKeyPair(privateKey)
    val createTunnelRes = wireguardRepository.createOrUpdate(
      vpnProfile = profile,
      keyPair = keyPair,
      serverId = serverId,
    )
    createTunnelRes
      .getOrNull()
      ?: return Either.Left(Error.CreateTunnel)

    wireguardRepository.setTunnelState(
      tunnelName = createTunnelRes.requireRight().name,
      tunnelState = VpnTunnel.State.UP,
    ).getOrNull()
      ?: return Either.Left(Error.SetTunnelState)

    repository.resetConnection()

    return Either.Right(Unit)
  }

  private suspend fun connectV2Ray(
    serverId: String,
    profile: V2RayVpnProfile?,
  ): Either<Error, Unit> {
    profile ?: return Either.Left(Error.ParseProfile)
    v2RayRepository.startV2Ray(
      profile = profile,
      serverId = serverId,
      serverName = "BasedVPN server",
    ).getOrNull()
      ?: return Either.Left(Error.StartV2Ray)

    repository.resetConnection()

    return Either.Right(Unit)
  }

  private suspend fun disconnectV2Ray() {
    v2RayRepository.stopV2ray()
  }

  private suspend fun disconnectWireguard() {
    val tunnel = wireguardRepository
      .getTunnel(DefaultTunnelName)
      ?: return
    wireguardRepository.setTunnelState(
      tunnelName = tunnel.name,
      tunnelState = VpnTunnel.State.DOWN,
    )
  }

  sealed interface Error : BaseError {
    data class GetCredentials(val error: String?) : Error {
      override val message: String = "Get Credentials error: ${error ?: "Unknown"}"
    }

    data object NotEnrolled : Error {
      override val message: String = "User not enrolled"
    }

    data object Banned : Error {
      override val message: String = "User has been banned"
    }

    data object TokenExpired : Error {
      override val message: String = "Token has been expired"
    }

    data object ParseProfile : Error {
      override val message: String = "Parse profile error"
    }

    data object RequestPermissions : Error {
      override val message: String = "VPN Permission wasn't granted"
    }

    data object CreateTunnel : Error {
      override val message: String = "Create Wireguard tunnel error"
    }

    data object SetTunnelState : Error {
      override val message: String = "Set Wireguard tunnel state error"
    }

    data object StartV2Ray : Error {
      override val message: String = "Start V2Ray error"
    }
  }
}

enum class PermissionStatus {
  Requested, Allowed, Denied
}
