package co.uk.basedapps.blockchain.transaction

import arrow.core.Either
import arrow.core.flatMap
import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.exception.Failure
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
    return subscribePayload
      .onLeft { Timber.e("Subscribe to node failed: can't create payload message") }
      .flatMap { payload ->
        walletRepository.signRequestAndBroadcastJson(
          messages = listOf(payload),
          gasPrice = gasPrice,
          chainId = chainId,
          granter = granter,
        )
      }
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
    return subscribePayload
      .onLeft { Timber.d("Subscribe to plan failed: can't  create payload message") }
      .flatMap { payload ->
        walletRepository.signRequestAndBroadcastJson(
          messages = listOf(payload),
          gasPrice = gasPrice,
          chainId = chainId,
          granter = granter,
        )
      }
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
    return transferPayload
      .flatMap { payload ->
        walletRepository.signRequestAndBroadcastJson(
          messages = listOf(payload),
          gasPrice = gasPrice,
          chainId = chainId,
          granter = granter,
        )
      }
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
    return connectMessage
      .flatMap { message ->
        walletRepository.signRequestAndBroadcastJson(
          messages = message,
          gasPrice = gasPrice,
          chainId = chainId,
          granter = granter,
        )
      }
  }

  suspend fun fetchTransaction(txHash: String): Either<Failure, String> {
    return walletRepository.fetchTransactionJson(txHash)
  }
}
