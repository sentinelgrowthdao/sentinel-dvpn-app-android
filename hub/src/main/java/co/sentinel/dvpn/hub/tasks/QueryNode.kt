package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import sentinel.node.v2.Querier
import sentinel.node.v2.QueryServiceGrpc
import timber.log.Timber

class QueryNode(
  private val app: BaseCosmosApp,
) {
  suspend fun execute(nodeAddress: String) = kotlin.runCatching {
    val stub = QueryServiceGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
    val response = stub.queryNode(
      Querier.QueryNodeRequest.newBuilder()
        .setAddress(nodeAddress)
        .build(),
    ).await()
    Either.Right(response.node)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
