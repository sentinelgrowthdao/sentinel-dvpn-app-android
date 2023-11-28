package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.network.ChannelBuilder.TIME_OUT
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import sentinel.node.v2.Querier
import sentinel.node.v2.QueryServiceGrpc
import timber.log.Timber

object QueryNode {
  suspend fun execute(nodeAddress: String) = kotlin.runCatching {
    val stub = QueryServiceGrpc.newFutureStub(ChannelBuilder.getMainChannel())
      .withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
    val response = stub.queryNode(
      Querier.QueryNodeRequest.newBuilder()
        .setAddress(nodeAddress)
        .build(),
    ).await()
    Either.Right(response.node)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
