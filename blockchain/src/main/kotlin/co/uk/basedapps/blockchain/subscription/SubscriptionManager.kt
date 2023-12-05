package co.uk.basedapps.blockchain.subscription

import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.functional.requireRight
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionManager
@Inject constructor(
  private val walletRepository: WalletRepository,
  private val hubRepository: HubRemoteRepository,
) {

  suspend fun subscribeToNode(
    nodeAddress: String,
    denom: String,
    gigabytes: Long,
    hours: Long,
    gasPrice: Long,
    chainId: String,
  ): Either<Unit, Unit> {
    val subscribePayload = hubRepository.generateSubscribePayload(
      nodeAddress = nodeAddress,
      denom = denom,
      gigabytes = gigabytes,
      hours = hours,
    )
    if (subscribePayload.isLeft) {
      return Either.Left(Unit)
    }

    return walletRepository.signSubscribedRequestAndBroadcast(
      nodeAddress = nodeAddress,
      subscribeMessage = subscribePayload.requireRight(),
      gasPrice = gasPrice,
      chainId = chainId,
    )
  }
}
