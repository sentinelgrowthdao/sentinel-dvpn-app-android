package co.uk.basedapps.blockchain.transaction

import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.functional.requireRight
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class TransactionManager
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
    granter: String?,
  ): Either<Failure, String> {
    Timber.d("Subscribe to node $nodeAddress")
    val subscribePayload = hubRepository.generateNodeSubscriptionPayload(
      nodeAddress = nodeAddress,
      denom = denom,
      gigabytes = gigabytes,
      hours = hours,
    )
    if (subscribePayload.isLeft) {
      Timber.e("Subscribe to node failed: can't create payload message")
      return Either.Left(Failure.AppError)
    }

    return walletRepository.signRequestAndBroadcastJson(
      messages = listOf(subscribePayload.requireRight()),
      gasPrice = gasPrice,
      chainId = chainId,
      granter = granter,
    )
  }

  suspend fun subscribeToPlan(
    planId: Long,
    denom: String,
    gasPrice: Long,
    chainId: String,
    granter: String?,
  ): Either<Failure, String> {
    Timber.d("Subscribe to plan $planId")
    val subscribePayload = hubRepository.generatePlanSubscriptionPayload(
      planId = planId,
      denom = denom,
    )
    if (subscribePayload.isLeft) {
      Timber.d("Subscribe to plan failed: can't  create payload message")
      return Either.Left(Failure.AppError)
    }

    return walletRepository.signRequestAndBroadcastJson(
      messages = listOf(subscribePayload.requireRight()),
      gasPrice = gasPrice,
      chainId = chainId,
      granter = granter,
    )
  }

  suspend fun makeDirectTransfer(
    recipientAddress: String,
    amount: String,
    denom: String,
    gasPrice: Long,
    chainId: String,
    granter: String?,
  ): Either<Failure, String> {
    val transferPayload = hubRepository.generateDirectTransferPayload(
      recipientAddress = recipientAddress,
      amount = amount,
      denom = denom,
    )
    if (transferPayload.isLeft) {
      return Either.Left(Failure.AppError)
    }

    return walletRepository.signRequestAndBroadcastJson(
      messages = listOf(transferPayload.requireRight()),
      gasPrice = gasPrice,
      chainId = chainId,
      granter = granter,
    )
  }

  suspend fun startSession(
    subscriptionId: Long,
    nodeAddress: String,
    activeSession: Long?,
    gasPrice: Long,
    chainId: String,
    granter: String?,
  ): Either<Failure, String> {
    val connectMessage = hubRepository.generateConnectToNodeMessages(
      subscriptionId = subscriptionId,
      nodeAddress = nodeAddress,
      activeSessionId = activeSession,
    )
    if (connectMessage.isLeft) {
      return Either.Left(Failure.AppError)
    }

    return walletRepository.signRequestAndBroadcastJson(
      messages = connectMessage.requireRight(),
      gasPrice = gasPrice,
      chainId = chainId,
      granter = granter,
    )
  }

  suspend fun fetchTransaction(txHash: String): Either<Failure, String> {
    return walletRepository.fetchTransactionJson(txHash)
  }
}
