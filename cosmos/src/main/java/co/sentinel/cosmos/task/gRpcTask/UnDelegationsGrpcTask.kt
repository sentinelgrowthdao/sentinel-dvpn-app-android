package co.sentinel.cosmos.task.gRpcTask

import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.dao.Account
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import com.google.protobuf.ByteString
import cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse
import cosmos.base.query.v1beta1.Pagination.PageRequest
import cosmos.staking.v1beta1.QueryGrpc
import cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest
import cosmos.staking.v1beta1.Staking.UnbondingDelegation
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.guava.await
import timber.log.Timber

class UnDelegationsGrpcTask(
  app: BaseCosmosApp,
  private val mAccount: Account,
) : CommonTask(app) {
  private val mResultData = ArrayList<UnbondingDelegation>()
  private val mStub: QueryGrpc.QueryFutureStub

  init {
    mResult.taskType = BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS
    mStub = QueryGrpc.newFutureStub(app.channelBuilder.getMainChannel())
      .withDeadlineAfter(ChannelBuilder.TIME_OUT, TimeUnit.SECONDS)
  }

  override suspend fun doInBackground(vararg strings: String): TaskResult {
    try {
      val request = QueryDelegatorUnbondingDelegationsRequest.newBuilder()
        .setDelegatorAddr(mAccount.address).build()
      val response = mStub.delegatorUnbondingDelegations(request).await()
      mResultData.addAll(response.unbondingResponsesList)
      mResult.isSuccess = true
      mResult.resultData = mResultData
    } catch (e: Exception) {
      Timber.e("UnDelegationsGrpcTask " + e.message)
    }
    return mResult
  }

  private suspend fun pageJob(nextKey: ByteString): QueryAllBalancesResponse? {
    try {
      val pageRequest = PageRequest.newBuilder().setKey(nextKey).build()
      val request =
        QueryDelegatorUnbondingDelegationsRequest.newBuilder().setPagination(pageRequest)
          .setDelegatorAddr(mAccount.address).build()
      val response = mStub.delegatorUnbondingDelegations(request).await()
      mResultData.addAll(response.unbondingResponsesList)
      if (response.hasPagination() && response.pagination.nextKey.size() > 0) {
        pageJob(response.pagination.nextKey)
      }
    } catch (e: Exception) {
      Timber.e("UnDelegationsGrpcTask pageJob " + e.message)
    }
    return null
  }
}
