package co.sentinel.cosmos.task.gRpcTask.broadcast

import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import com.google.protobuf.util.JsonFormat
import cosmos.tx.v1beta1.ServiceGrpc
import cosmos.tx.v1beta1.ServiceOuterClass
import io.grpc.Status.Code
import io.grpc.StatusRuntimeException
import java.net.UnknownHostException
import kotlinx.coroutines.guava.await
import timber.log.Timber

class FetchTransactionTask(
  app: BaseCosmosApp,
  private val txHash: String,
) : CommonTask(app) {

  private val jsonFormatter = JsonFormat.printer()
    .includingDefaultValueFields()

  init {
    mResult.taskType = BaseConstant.TASK_GRPC_BROAD_SEND
  }

  override suspend fun doInBackground(vararg strings: String): TaskResult {
    try {
      val txService = ServiceGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      val request = ServiceOuterClass.GetTxRequest.newBuilder().setHash(txHash).build()
      val response = txService.getTx(request).await()
      mResult.resultData = response.txResponse.txhash
      mResult.resultJson = jsonFormatter.print(response.txResponse)
      if (response.txResponse.code > 0) {
        mResult.errorCode = response.txResponse.code
        mResult.errorMsg = response.txResponse.rawLog
        mResult.isSuccess = false
      } else {
        mResult.isSuccess = true
      }
    } catch (e: Exception) {
      Timber.e("FetchTransactionTask exception: " + e.message)
      if (e.cause?.cause is UnknownHostException) {
        mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK
      }
      if ((e as StatusRuntimeException).status.code == Code.NOT_FOUND) {
        mResult.errorCode = TxNotFound
      }
      mResult.exception = e
      mResult.errorMsg = e.message.toString()
      mResult.isSuccess = false
    }
    return mResult
  }
}

const val TxNotFound = 404404
