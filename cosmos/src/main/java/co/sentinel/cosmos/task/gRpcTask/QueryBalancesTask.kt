package co.sentinel.cosmos.task.gRpcTask

import co.sentinel.cosmos.network.ChannelBuilder
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import cosmos.bank.v1beta1.QueryGrpc
import cosmos.bank.v1beta1.QueryOuterClass
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import timber.log.Timber

object QueryBalancesTask {
  suspend fun execute(
    address: String,
  ) = kotlin.runCatching {
    val stub = QueryGrpc.newFutureStub(ChannelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
    val request = QueryOuterClass.QueryAllBalancesRequest.newBuilder()
      .setAddress(address)
      .build()
    val response = stub.allBalances(request).await()
    Either.Right(response)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
