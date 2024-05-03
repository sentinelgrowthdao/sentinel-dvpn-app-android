package co.sentinel.dvpn.hub.tasks

import arrow.core.Either
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.uk.basedapps.domain.exception.Failure
import cosmos.feegrant.v1beta1.QueryGrpc
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import cosmos.feegrant.v1beta1.QueryOuterClass
import timber.log.Timber

class FetchFeegrant(
  private val app: BaseCosmosApp,
) {
  suspend fun execute(grantee: String, granter: String) = kotlin.runCatching {
    val stub = QueryGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
    val response = stub.allowance(
      QueryOuterClass.QueryAllowanceRequest.newBuilder()
        .setGrantee(grantee)
        .setGranter(granter)
        .build(),
    ).await()
    Either.Right(response.allowance)
  }.getOrElse {
    Timber.e(it)
    if (it.cause?.cause is UnknownHostException) {
      Either.Left(Failure.NetworkConnection)
    } else {
      Either.Left(Failure.AppError)
    }
  }
}
