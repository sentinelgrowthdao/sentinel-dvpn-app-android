package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.base.BaseChain
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.network.ChannelBuilder.TIME_OUT
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import cosmos.base.query.v1beta1.Pagination
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import sentinel.plan.v2.Querier
import sentinel.plan.v2.QueryServiceGrpc
import sentinel.types.v1.StatusOuterClass
import timber.log.Timber

object QueryPlans {
  suspend fun execute(
    chain: BaseChain,
    offset: Long = 0,
    limit: Long = 30,
  ) = kotlin.runCatching {
    val stub = QueryServiceGrpc.newFutureStub(ChannelBuilder.getChain(chain))
      .withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
    val response = stub.queryPlans(
      Querier.QueryPlansRequest.newBuilder()
        .setStatus(StatusOuterClass.Status.STATUS_ACTIVE)
        .setPagination(
          Pagination.PageRequest.newBuilder()
            .setLimit(limit)
            .setOffset(offset),
        ).build(),
    ).await()
    Either.Right(response)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
