package co.sentinel.cosmos.task.gRpcTask

import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import cosmos.bank.v1beta1.QueryGrpc
import cosmos.bank.v1beta1.QueryOuterClass
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import timber.log.Timber

class QueryBalancesTask(
  app: BaseCosmosApp,
  private val address: String,
) : CommonTask(app) {

  private val mStub: QueryGrpc.QueryFutureStub

  init {
    mResult.taskType = BaseConstant.TASK_GRPC_FETCH_BALANCE
    mStub = QueryGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
  }

  override suspend fun doInBackground(vararg strings: String): TaskResult {
    try {
      val request = QueryOuterClass.QueryAllBalancesRequest.newBuilder().setAddress(address).build()
      val response = mStub.allBalances(request).await()
      mResult.isSuccess = true
      mResult.resultData = response
    } catch (e: Exception) {
      Timber.e("BalanceGrpcTask " + e.message)
      mResult.exception = e
    }
    return mResult
  }
}
