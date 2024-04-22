package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import sentinel.session.v2.Querier
import sentinel.session.v2.QueryServiceGrpc
import timber.log.Timber

class FetchSessions(
  private val app: BaseCosmosApp,
) {
  suspend fun execute(address: String) = kotlin.runCatching {
    val stub = QueryServiceGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
    val response = stub.querySessionsForAccount(
      Querier.QuerySessionsForAccountRequest.newBuilder()
        .setAddress(address)
        .build(),
    ).await()
    Either.Right(response.sessionsList)
  }.getOrElse {
    Timber.e(it)
    if (it.cause?.cause is UnknownHostException) {
      Either.Left(Failure.NetworkConnection)
    } else {
      Either.Left(Failure.AppError)
    }
  }
}
