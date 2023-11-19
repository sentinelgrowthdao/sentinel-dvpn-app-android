package co.sentinel.cosmos.task.gRpcTask

import co.sentinel.cosmos.base.BaseChain
import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.task.CommonTask
import co.sentinel.cosmos.task.TaskResult
import com.google.protobuf.ByteString
import cosmos.bank.v1beta1.QueryGrpc
import cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesRequest
import cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse
import cosmos.base.query.v1beta1.Pagination.PageRequest
import cosmos.base.v1beta1.CoinOuterClass
import kotlinx.coroutines.guava.await
import timber.log.Timber
import java.util.concurrent.TimeUnit


class BalanceGrpcTask(
    app: BaseCosmosApp,
    private val mChain: BaseChain,
    private val mAddress: String
) : CommonTask(app) {
    private val mResultData = ArrayList<CoinOuterClass.Coin>()
    private val mStub: QueryGrpc.QueryFutureStub

    init {
        mResult.taskType = BaseConstant.TASK_GRPC_FETCH_BALANCE
        mStub = QueryGrpc.newFutureStub(ChannelBuilder.getChain(mChain))
            .withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
    }

    override suspend fun doInBackground(vararg strings: String): TaskResult {
        try {
            val request = QueryAllBalancesRequest.newBuilder().setAddress(mAddress).build()
            val response = mStub.allBalances(request).await()
            mResultData.addAll(response.balancesList)
            //            Timber.w("response " + response.getBalancesList());

//            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
//                pageJob(response.getPagination().getNextKey());
//            }
            mResult.isSuccess = true
            mResult.resultData = mResultData
            //            Timber.w("Balance " + mResultData.size());
        } catch (e: Exception) {
            Timber.e("BalanceGrpcTask " + e.message)
        }
        return mResult
    }

    private suspend fun pageJob(nextKey: ByteString): QueryAllBalancesResponse? {
        try {
            val pageRequest = PageRequest.newBuilder().setKey(nextKey).build()
            val request =
                QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(mAddress)
                    .build()
            val response = mStub.allBalances(request).await()
            mResultData.addAll(response.balancesList)
            if (response.hasPagination() && response.pagination.nextKey.size() > 0) {
                pageJob(response.pagination.nextKey)
            }
        } catch (e: Exception) {
            Timber.e("BalanceGrpcTask pageJob " + e.message)
        }
        return null
    }
}
