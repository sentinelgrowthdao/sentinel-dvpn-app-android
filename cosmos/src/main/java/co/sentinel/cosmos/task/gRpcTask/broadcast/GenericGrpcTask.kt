package co.sentinel.cosmos.task.gRpcTask.broadcast

import co.sentinel.cosmos.R
import co.sentinel.cosmos.base.BaseChain
import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.cosmos.Signer
import co.sentinel.cosmos.crypto.CryptoHelper
import co.sentinel.cosmos.dao.Account
import co.sentinel.cosmos.model.type.Fee
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import co.sentinel.cosmos.utils.WKey
import com.google.protobuf.util.JsonFormat
import com.google.protobuf2.Any
import cosmos.auth.v1beta1.QueryGrpc
import cosmos.auth.v1beta1.QueryOuterClass
import cosmos.auth.v1beta1.QueryOuterClass.QueryAccountResponse
import cosmos.tx.v1beta1.ServiceGrpc
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import org.bitcoinj.crypto.DeterministicKey
import timber.log.Timber

class GenericGrpcTask(
  app: BaseCosmosApp,
  private val mAccount: Account,
  private val mMessages: List<Any>,
  private val mFees: Fee,
  private val mChainId: String,
) : CommonTask(app) {
  private var mAuthResponse: QueryAccountResponse? = null
  private var deterministicKey: DeterministicKey? = null

  private val jsonFormatter = JsonFormat.printer()
    .includingDefaultValueFields()

  init {
    mResult.taskType = BaseConstant.TASK_GRPC_BROAD_SEND
  }

  override suspend fun doInBackground(vararg strings: String): TaskResult {
    try {
      val entropy = CryptoHelper.doDecryptData(
        app.context.getString(R.string.key_mnemonic) + mAccount.uuid,
        mAccount.resource,
        mAccount.spec,
      )
      deterministicKey = WKey.getKeyWithPathfromEntropy(
        BaseChain.getChain(mAccount.baseChain),
        entropy,
        mAccount.path.toInt(),
        mAccount.newBip44,
      )
      val authStub = QueryGrpc.newFutureStub(ChannelBuilder.getMainChannel())
        .withDeadlineAfter(ChannelBuilder.TIME_OUT_BROADCAST, TimeUnit.SECONDS)
      val request =
        QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address)
          .build()
      mAuthResponse = authStub.account(request).await()

      // broadCast
      val txService = ServiceGrpc.newFutureStub(ChannelBuilder.getMainChannel())
      val broadcastTxRequest = Signer.getGrpcGenericReq(
        mAuthResponse,
        mFees,
        mMessages,
        deterministicKey,
        mChainId,
      )

      val response = txService.broadcastTx(broadcastTxRequest).await()
      mResult.resultData = response.txResponse.txhash
      mResult.resultJson = jsonFormatter.print(response.txResponse)
      if (response.txResponse.code > 0) {
        mResult.errorCode = response.txResponse.code
        mResult.errorMsg = response.txResponse.raTimber
        mResult.isSuccess = false
      } else {
        mResult.isSuccess = true
      }
    } catch (e: Exception) {
      Timber.e("GenericGrpcTask exception: " + e.message)
      if (e.cause?.cause is UnknownHostException) {
        mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK
        mResult.errorMsg = e.message.toString()
      }
      mResult.exception = e
      mResult.isSuccess = false
    }
    return mResult
  }
}
