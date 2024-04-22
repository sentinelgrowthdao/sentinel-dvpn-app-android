package co.sentinel.cosmos.task.gRpcTask

import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import cosmos.base.tendermint.v1beta1.Query.GetNodeInfoRequest
import cosmos.base.tendermint.v1beta1.ServiceGrpc
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import timber.log.Timber

class NodeInfoGrpcTask(app: BaseCosmosApp) : CommonTask(app) {
  private val mStub: ServiceGrpc.ServiceFutureStub

  init {
    mResult.taskType = BaseConstant.TASK_GRPC_FETCH_NODE_INFO
    mStub = ServiceGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
  }

  override suspend fun doInBackground(vararg strings: String): TaskResult {
    try {
      val request = GetNodeInfoRequest.newBuilder().build()
      val response = mStub.getNodeInfo(request).await()
      mResult.isSuccess = true
      mResult.resultData = response.defaultNodeInfo
    } catch (e: Exception) {
      Timber.e("NodeInfoGrpcTask " + e.message)
    }
    return mResult
  }
}
