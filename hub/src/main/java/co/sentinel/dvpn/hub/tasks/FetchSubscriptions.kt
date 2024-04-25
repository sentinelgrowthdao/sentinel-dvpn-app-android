package co.sentinel.dvpn.hub.tasks

import arrow.core.Either
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.uk.basedapps.domain.exception.Failure
import cosmos.base.query.v1beta1.Pagination
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import sentinel.subscription.v2.Querier
import sentinel.subscription.v2.QueryServiceGrpc
import timber.log.Timber

class FetchSubscriptions(
  private val app: BaseCosmosApp,
) {
  suspend fun execute(
    address: String,
    offset: Long = 0,
    limit: Long = 10,
  ) = kotlin.runCatching {
    val stub = QueryServiceGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
    val response = stub.querySubscriptionsForAccount(
      Querier.QuerySubscriptionsForAccountRequest.newBuilder()
        .setAddress(address)
        .setPagination(
          Pagination.PageRequest.newBuilder()
            .setLimit(limit)
            .setOffset(offset),
        )
        .build(),
    ).await()
    Either.Right(response)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
