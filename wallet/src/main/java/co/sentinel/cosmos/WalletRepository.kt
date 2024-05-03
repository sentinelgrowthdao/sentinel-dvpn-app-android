package co.sentinel.cosmos

import android.util.Base64
import arrow.core.Either
import arrow.core.flatMap
import co.sentinel.cosmos.base.BaseChain
import co.sentinel.cosmos.base.BaseConstant
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.base.BaseData
import co.sentinel.cosmos.core.store.BalanceStoreImpl
import co.sentinel.cosmos.core.store.PrefsStore
import co.sentinel.cosmos.cosmos.Signer
import co.sentinel.cosmos.crypto.CryptoHelper
import co.sentinel.cosmos.dao.Account
import co.sentinel.cosmos.dto.CurrentPrice
import co.sentinel.cosmos.dto.GeneratedKeyword
import co.sentinel.cosmos.model.type.AccountBalance
import co.sentinel.cosmos.model.type.Coin
import co.sentinel.cosmos.network.station.StationApi
import co.sentinel.cosmos.task.TaskResult
import co.sentinel.cosmos.task.gRpcTask.AllRewardGrpcTask
import co.sentinel.cosmos.task.gRpcTask.AuthGrpcTask
import co.sentinel.cosmos.task.gRpcTask.BondedValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.DelegationsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.NodeInfoGrpcTask
import co.sentinel.cosmos.task.gRpcTask.QueryBalancesTask
import co.sentinel.cosmos.task.gRpcTask.UnBondedValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.UnBondingValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.UnDelegationsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.FetchTransactionTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.GenericGrpcTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.TxNotFound
import co.sentinel.cosmos.task.userTask.GenerateAccountTask
import co.sentinel.cosmos.task.userTask.GenerateSentinelAccountTask
import co.sentinel.cosmos.utils.GasFee
import co.sentinel.cosmos.utils.WDp
import co.sentinel.cosmos.utils.WKey
import co.sentinel.cosmos.utils.WUtil
import co.sentinel.cosmos.utils.toByteArray
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.exception.logNonFatal
import com.google.protobuf.Any
import com.google.protobuf.util.JsonFormat
import cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse
import cosmos.distribution.v1beta1.Distribution
import cosmos.staking.v1beta1.Staking
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import tendermint.p2p.Types
import timber.log.Timber

@Singleton
class WalletRepository
@Inject constructor(
  private val app: BaseCosmosApp,
  private val stationApi: StationApi,
) {
  private val prefsStore = PrefsStore(app)
  private val balanceStore = BalanceStoreImpl(app)
  private val jsonFormatter = JsonFormat.printer()
    .includingDefaultValueFields()

  suspend fun generateAccount(): Either<Unit, Unit> {
    return kotlin.runCatching {
      if (!app.baseDao.hasUser()) {
        val keywords: ArrayList<String> = ArrayList()
        val entropy = WKey.getEntropy()
        keywords.addAll(WKey.getRandomMnemonic(entropy))
        val size = keywords.size
        val chain = BaseChain.SENTINEL_MAIN
        val bip44 = false
        GenerateAccountTask(app, chain, bip44)
          .run("0", WUtil.ByteArrayToHexString(entropy), "$size")
          .let {
            if (it.isSuccess) {
              Either.Right(Unit)
            } else {
              Either.Left(Unit)
            }
          }
      } else {
        Either.Right(Unit)
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun restoreAccount(keywords: String): Either<Unit, Unit> {
    return kotlin.runCatching {
      val keywordList = ArrayList<String>()
      keywordList.addAll(keywords.split(" "))
      val entropy = WUtil.ByteArrayToHexString(WKey.toEntropy(keywordList))
      val size = keywordList.size
      val chain = BaseChain.SENTINEL_MAIN
      val bip44 = false
      GenerateAccountTask(app, chain, bip44)
        .run("0", entropy, "$size").let {
          if (it.isSuccess) {
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun generateSentinelAccount(
    entropy: String,
    keywords: List<String>,
  ): Either<Unit, Unit> {
    return kotlin.runCatching {
      GenerateSentinelAccountTask(app, entropy, keywords).run().let {
        if (it.isSuccess) {
          Either.Right(Unit)
        } else if (it.errorCode == 7001) {
          // TODO change this type of error handling
          Either.Left(Unit)
        } else {
          Either.Left(Unit)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun fetchCurrentPrice(): Either<Unit, CurrentPrice> {
    return kotlin.runCatching {
      val priceResponse = stationApi.getPrice(WUtil.marketPrice(BaseChain.SENTINEL_MAIN))
      when {
        priceResponse.isSuccessful -> {
          priceResponse.body()?.let { prices ->
            for (price in prices) {
              app.baseDao.mPrices.add(price)
            }

            val baseChain = BaseChain.SENTINEL_MAIN
            val baseDao: BaseData = app.baseDao
            val denom = WDp.mainDenom(baseChain)
            val price = WDp.dpPerUserCurrencyValue(baseDao, denom)
            val upDownPrice = WDp.dpValueChange(baseDao, denom)
            val lastUpDown = WDp.valueChange(baseDao, denom)

            Either.Right(
              CurrentPrice(
                price.toString(),
                upDownPrice.toString(),
                lastUpDown,
              ),
            )
          } ?: Either.Left(Unit)
        }

        else -> Either.Left(Unit)
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  fun generateKeywords(): Either<Unit, GeneratedKeyword> {
    return kotlin.runCatching {
      val entropy = WKey.getEntropy()
      val entropyString = WUtil.ByteArrayToHexString(entropy)
      val keywordList = ArrayList<String>()
      keywordList.addAll(WKey.getRandomMnemonic(entropy))
      val chain = BaseChain.SENTINEL_MAIN
      val bip44 = false
      val dKey = WKey.getKeyWithPathfromEntropy(
        chain,
        entropyString,
        0,
        bip44,
      )
      val address = WKey.getDpAddress(chain, dKey.publicKeyAsHex)

      Either.Right(GeneratedKeyword(keywordList, address, entropyString))
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  private fun getAccount(): Account? = try {
    app.baseDao.onSelectAccount(app.baseDao.lastUser)
  } catch (e: Exception) {
    Timber.e("Failed to get account: $e")
    null
  }

  fun getAccountAddress(): String? = getAccount()?.address

  fun getSignature(sessionId: Long): Either<Unit, String> {
    val account = getAccount() ?: return Either.Left(Unit)
    val entropy = CryptoHelper.doDecryptData(
      app.context.getString(R.string.key_mnemonic) + account.uuid,
      account.resource,
      account.spec,
    )
    val deterministicKey = WKey.getKeyWithPathfromEntropy(
      BaseChain.getChain(account.baseChain),
      entropy,
      account.path.toInt(),
      account.newBip44,
    )
    return Signer.getGrpcByteSingleSignature(deterministicKey, sessionId.toByteArray()).let {
      Either.Right(Base64.encodeToString(it, Base64.DEFAULT))
    }
  }

  private suspend fun signRequestAndBroadcast(
    messages: List<Any>,
    gasPrice: Long? = null,
    chainId: String? = null,
    granter: String? = null,
  ): Either<Failure, TaskResult> {
    val account = getAccount() ?: return Either.Left(Failure.AppError)
    val fee = gasPrice?.let { GasFee.composeFee(gasPrice, granter) } ?: GasFee.DEFAULT_FEE
    val chainIdLocal = chainId ?: app.baseDao.chainIdGrpc
    fetchAll(account)
    val taskResult = GenericGrpcTask(app, account, messages, fee, chainIdLocal)
      .run(prefsStore.retrievePasscode()) // password confirmation
    taskResult.log()
    return Either.Right(taskResult)
  }

  suspend fun signRequestAndBroadcastResult(
    messages: List<Any>,
    gasPrice: Long? = null,
    chainId: String? = null,
    granter: String? = null,
  ): Either<Failure, Unit> {
    val taskResult = signRequestAndBroadcast(messages, gasPrice, chainId, granter)
    return taskResult.flatMap { result ->
      if (!result.isSuccess) {
        val typeUrls = messages.joinToString(separator = "\n") { it.typeUrl }
        Timber.e(
          "Failed to broadcast message!\nMessages sent:\n$typeUrls\n" +
            "Error code: ${result.errorCode}\nError message: ${result.errorMsg}\n" +
            "Error response: ${result.resultJson}",
        )
        when (result.errorCode) {
          BaseConstant.ERROR_CODE_NETWORK -> Either.Left(Failure.NetworkConnection)
          BaseConstant.ERROR_INSUFFICIENT_FUNDS -> Either.Left(Failure.InsufficientFunds)
          else -> Either.Left(Failure.AppError)
        }
      } else {
        Either.Right(Unit)
      }
    }
  }

  suspend fun signRequestAndBroadcastJson(
    messages: List<Any>,
    gasPrice: Long? = null,
    chainId: String? = null,
    granter: String? = null,
  ): Either<Failure, String> {
    val taskResult = signRequestAndBroadcast(messages, gasPrice, chainId, granter)
    return taskResult.flatMap { result ->
      val typeUrls = messages.joinToString(separator = "\n") { it.typeUrl }
      when {
        result.isSuccess -> {
          Timber.d("Broadcast was successful:\nMessages sent:\n$typeUrls\n")
          Either.Right(result.resultJson.orEmpty())
        }

        result.errorCode == BaseConstant.ERROR_CODE_UNKNOWN ||
          result.errorCode == BaseConstant.ERROR_CODE_NETWORK -> {
          Timber.e("Broadcasting failed with an exception!")
          Either.Left(Failure.AppError)
        }

        else -> {
          Timber.e(
            "Failed to broadcast message!\nMessages sent:\n$typeUrls\n" +
              "Error code: ${result.errorCode}\nError message: ${result.errorMsg}\n" +
              "Error response: ${result.resultJson}",
          )
          Either.Right(result.resultJson.orEmpty())
        }
      }
    }
  }

  private suspend fun fetchTransaction(txHash: String): Either<Failure, TaskResult> {
    Timber.d("Fetch transaction by hash: $txHash")
    val taskResult = FetchTransactionTask(app, txHash)
      .run(prefsStore.retrievePasscode())
    taskResult.log()
    return Either.Right(taskResult)
  }

  suspend fun fetchTransactionJson(txHash: String): Either<Failure, String> {
    val taskResult = fetchTransaction(txHash)
    return taskResult.flatMap { result ->
      when {
        result.isSuccess -> {
          Timber.d("Fetched transaction is successful!")
          Either.Right(result.resultJson.orEmpty())
        }

        result.errorCode == TxNotFound -> {
          Timber.e("Transaction was not found!")
          Either.Left(Failure.NotFound)
        }

        result.errorCode == BaseConstant.ERROR_CODE_UNKNOWN ||
          result.errorCode == BaseConstant.ERROR_CODE_NETWORK -> {
          Timber.e("Failed to fetch transaction!")
          Either.Left(Failure.AppError)
        }

        else -> {
          Timber.e(
            "Fetched transaction is failed!\n" +
              "Error code: ${result.errorCode}\nError message: ${result.errorMsg}\n" +
              "Error response: ${result.resultJson}",
          )
          Either.Right(result.resultJson.orEmpty())
        }
      }
    }
  }

  suspend fun fetchNodeInfo(): Either<Unit, Unit> =
    kotlin.runCatching {
      NodeInfoGrpcTask(app).run().let {
        if (it.resultData is Types.DefaultNodeInfo) {
          app.baseDao.mGRpcNodeInfo = it.resultData as Types.DefaultNodeInfo
          Either.Right(Unit)
        } else {
          Either.Left(Unit)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchAuthorization(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      AuthGrpcTask(app, account.address).run().let {
        if (it.resultData is Any) {
          app.baseDao.mGRpcAccount = it.resultData as Any
          Either.Right(Unit)
        } else {
          Either.Left(Unit)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchBondedValidators(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      BondedValidatorsGrpcTask(app, BaseChain.getChain(account.baseChain)).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Staking.Validator } == true
          ) {
            app.baseDao.mGRpcTopValidators.clear()
            app.baseDao.mGRpcTopValidators.addAll(result.resultData as ArrayList<Staking.Validator>)
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchUnbondedValidators(): Either<Unit, Unit> =
    kotlin.runCatching {
      UnBondedValidatorsGrpcTask(app).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Staking.Validator } == true
          ) {
            app.baseDao.mGRpcUnbondedValidators.clear()
            app.baseDao.mGRpcUnbondedValidators.addAll(result.resultData as ArrayList<Staking.Validator>)
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchUnbondingValidators(): Either<Unit, Unit> =
    kotlin.runCatching {
      UnBondingValidatorsGrpcTask(app).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Staking.Validator } == true
          ) {
            app.baseDao.mGRpcUnbondingValidators.clear()
            app.baseDao.mGRpcUnbondingValidators.addAll(result.resultData as ArrayList<Staking.Validator>)
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  private suspend fun getBalancesByAddress(
    address: String,
  ): Either<Failure, QueryAllBalancesResponse> {
    val balancesResult = QueryBalancesTask(app, address)
      .run(prefsStore.retrievePasscode())
    return if (balancesResult.isSuccess) {
      Either.Right(balancesResult.resultData as QueryAllBalancesResponse)
    } else {
      Either.Left(Failure.AppError)
    }
  }

  suspend fun getBalancesByAddressJson(
    address: String,
  ): Either<Failure, String> = getBalancesByAddress(address = address)
    .map { result -> jsonFormatter.print(result) }

  suspend fun fetchBalance(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      val chain = BaseChain.getChain(account.baseChain)
      val result = getBalancesByAddress(address = account.address)
      result.map { response ->
        val balance = response.balancesList
        app.baseDao.mGrpcBalance.clear()
        if (balance.size > 0) {
          for (coin in balance) {
            app.baseDao.mGrpcBalance.add(Coin(coin.denom, coin.amount))
          }
        } else {
          app.baseDao.mGrpcBalance.add(
            Coin(WDp.mainDenom(chain), "0"),
          )
        }

        /**
         * Save balance with fetch time in balance preferences.
         */
        balanceStore.storeBalance(
          AccountBalance(
            app.baseDao.mGrpcBalance,
            System.currentTimeMillis(),
          ),
        )
      }
        .mapLeft { }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchDelegations(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      DelegationsGrpcTask(app, account).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Staking.DelegationResponse } == true
          ) {
            app.baseDao.mGrpcDelegations.clear()
            app.baseDao.mGrpcDelegations =
              result.resultData as ArrayList<Staking.DelegationResponse>
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchUnboundingDelegations(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      UnDelegationsGrpcTask(app, account).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Staking.UnbondingDelegation } == true
          ) {
            app.baseDao.mGrpcUndelegations.clear()
            app.baseDao.mGrpcUndelegations =
              result.resultData as ArrayList<Staking.UnbondingDelegation>
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchRewards(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      AllRewardGrpcTask(app, account).run()
        .let { result ->
          if (result.resultData is ArrayList<*> && (result.resultData as ArrayList<*>).firstOrNull()
              ?.let { it is Distribution.DelegationDelegatorReward } == true
          ) {
            app.baseDao.mGrpcRewards.clear()
            app.baseDao.mGrpcRewards =
              result.resultData as ArrayList<Distribution.DelegationDelegatorReward>
            Either.Right(Unit)
          } else {
            Either.Left(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchBalanceCache(): Either<Unit, AccountBalance?> {
    return Either.Right(balanceStore.getBalance())
  }

  suspend fun fetchBalance(): Either<Unit, AccountBalance?> {
    val account = getAccount() ?: return Either.Left(Unit)
    return fetchBalance(account).let {
      Either.Right(balanceStore.getBalance())
    }
  }

  private suspend fun fetchAll(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      withContext(Dispatchers.Default) {
        listOf(
          async(start = CoroutineStart.LAZY) { fetchNodeInfo() },
          async(start = CoroutineStart.LAZY) { fetchAuthorization(account) },
          // not necessary for now
//          async(start = CoroutineStart.LAZY) { fetchBondedValidators(account) },
//          async(start = CoroutineStart.LAZY) { fetchUnbondedValidators() },
//          async(start = CoroutineStart.LAZY) { fetchUnbondingValidators() },
//          async(start = CoroutineStart.LAZY) { fetchBalance(account) },
//          async(start = CoroutineStart.LAZY) { fetchDelegations(account) },
//          async(start = CoroutineStart.LAZY) { fetchUnboundingDelegations(account) },
//          async(start = CoroutineStart.LAZY) { fetchRewards(account) },
        ).awaitAll().let { results ->
          if (results.any { it.isLeft() }) {
            Either.Left(Unit)
          } else {
            Either.Right(Unit)
          }
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  fun clearWallet() {
    prefsStore.clear()
    app.baseDao.clearDB()
  }

  private fun TaskResult.log() {
    if (!isSuccess && errorCode != TxNotFound) {
      logNonFatal(
        message = resultJson ?: "Transaction failed",
        throwable = exception,
        tag = "Error code: $errorCode",
      )
    }
  }
}
