package co.sentinel.cosmos

import android.util.Base64
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
import co.sentinel.cosmos.dto.Session
import co.sentinel.cosmos.model.type.AccountBalance
import co.sentinel.cosmos.model.type.Coin
import co.sentinel.cosmos.model.type.Fee
import co.sentinel.cosmos.network.station.StationApi
import co.sentinel.cosmos.task.UserTask.GenerateAccountTask
import co.sentinel.cosmos.task.UserTask.GenerateSentinelAccountTask
import co.sentinel.cosmos.task.gRpcTask.AllRewardGrpcTask
import co.sentinel.cosmos.task.gRpcTask.AuthGrpcTask
import co.sentinel.cosmos.task.gRpcTask.BalanceGrpcTask
import co.sentinel.cosmos.task.gRpcTask.BondedValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.DelegationsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.NodeInfoGrpcTask
import co.sentinel.cosmos.task.gRpcTask.UnBondedValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.UnBondingValidatorsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.UnDelegationsGrpcTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.BroadcastNodeSubscribeGrpcTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.ConnectToNodeGrpcTask
import co.sentinel.cosmos.task.gRpcTask.broadcast.GenericGrpcTask
import co.sentinel.cosmos.utils.DEFAULT_FEE
import co.sentinel.cosmos.utils.DEFAULT_FEE_AMOUNT
import co.sentinel.cosmos.utils.DEFAULT_GAS
import co.sentinel.cosmos.utils.WDp
import co.sentinel.cosmos.utils.WKey
import co.sentinel.cosmos.utils.WUtil
import co.sentinel.cosmos.utils.denom
import co.sentinel.cosmos.utils.toByteArray
import co.uk.basedapps.domain.functional.Either
import com.google.protobuf2.Any
import cosmos.base.v1beta1.CoinOuterClass
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

  suspend fun generateKeywords(): Either<Unit, GeneratedKeyword> {
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

  fun getAccount() = app.baseDao.onSelectAccount(app.baseDao.lastUser)

  fun getAccountAddress(): String? = getAccount()?.address

  suspend fun signSubscribedRequestAndBroadcast(
    nodeAddress: String,
    subscribeMessage: Any,
  ): Either<Unit, Unit> {
    return kotlin.runCatching {
      val account = getAccount() ?: return@runCatching Either.Left(Unit)
      fetchAll(account)
      BroadcastNodeSubscribeGrpcTask(
        app,
        BaseChain.getChain(account.baseChain),
        account,
        nodeAddress,
        subscribeMessage,
        DEFAULT_FEE,
        app.baseDao.chainIdGrpc,
      ).run(prefsStore.retrievePasscode()) // password confirmation
        .let {
          if (!it.isSuccess) {
            Timber.e("${subscribeMessage.typeUrl}\n${it.errorMsg}")
            when (it.errorCode) {
              BaseConstant.ERROR_CODE_NETWORK -> Either.Left(Unit)
              else -> Either.Left(Unit)
            }
          } else {
            Either.Right(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun signRequestAndBroadcast(
    gasFactor: Int,
    messages: List<Any>,
  ): Either<Unit, Unit> {
    return kotlin.runCatching {
      val account = getAccount() ?: return@runCatching Either.Left(Unit)
      fetchAll(account)

      val gas = DEFAULT_GAS + (DEFAULT_GAS / 10 * gasFactor)
      val feePrice = DEFAULT_FEE_AMOUNT + (DEFAULT_FEE_AMOUNT / 10 * gasFactor)

      val fee = Fee(gas.toString(), arrayListOf(Coin(denom, feePrice.toString())))

      GenericGrpcTask(
        app,
        BaseChain.getChain(account.baseChain),
        account,
        messages,
        fee,
        app.baseDao.chainIdGrpc,
      ).run(prefsStore.retrievePasscode()) // password confirmation
        .let {
          if (!it.isSuccess) {
            when (it.errorCode) {
              BaseConstant.ERROR_CODE_NETWORK -> {
                Either.Left(Unit)
              }

              else -> {
                Either.Left(Unit)
              }
            }
          } else {
            Either.Right(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun getSignature(session: Session): String? {
    val account = getAccount() ?: return null
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
    return Signer.getGrpcByteSingleSignature(deterministicKey, session.id.toByteArray()).let {
      Base64.encodeToString(it, Base64.DEFAULT)
    }
  }

  suspend fun startNodeSession(messages: List<Any>): Either<Unit, Unit> {
    return kotlin.runCatching {
      val account = getAccount() ?: return@runCatching Either.Left(Unit)
      fetchAuthorization(account)
      if (app.baseDao.chainIdGrpc.isEmpty()) {
        fetchNodeInfo(account)
      }
      ConnectToNodeGrpcTask(
        app,
        BaseChain.getChain(account.baseChain),
        account,
        messages,
        DEFAULT_FEE,
        app.baseDao.chainIdGrpc,
      ).run(prefsStore.retrievePasscode()) // password confirmation
        .let { result ->
          if (!result.isSuccess) {
            val typeUrls = messages.map { "${it.typeUrl}\n" }
            Timber.e("$typeUrls\n${result.errorMsg}")
            when (result.errorCode) {
              BaseConstant.ERROR_CODE_NETWORK -> {
                Either.Left(Unit)
              }

              BaseConstant.ERROR_INSUFFICIENT_FUNDS -> {
                Either.Left(Unit)
              }

              else -> {
                Either.Left(Unit)
              }
            }
          } else {
            Either.Right(Unit)
          }
        }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)
  }

  suspend fun fetchNodeInfo(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      NodeInfoGrpcTask(app, BaseChain.getChain(account.baseChain)).run().let {
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
      AuthGrpcTask(app, BaseChain.getChain(account.baseChain), account.address).run().let {
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

  suspend fun fetchUnbondedValidators(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      UnBondedValidatorsGrpcTask(app, BaseChain.getChain(account.baseChain)).run()
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

  suspend fun fetchUnbondingValidators(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      UnBondingValidatorsGrpcTask(app, BaseChain.getChain(account.baseChain)).run()
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

  suspend fun fetchBalance(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      val chain = BaseChain.getChain(account.baseChain)
      BalanceGrpcTask(app, chain, account.address).run().let {
        if (it.resultData is ArrayList<*>) {
          val balance = it.resultData as ArrayList<CoinOuterClass.Coin>
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
          Either.Right(Unit)
        } else {
          Either.Left(Unit)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Unit)

  suspend fun getBalancesByAddress(
    address: String,
    baseChain: String,
  ): Either<Unit, List<Pair<Long, String>>> = kotlin.runCatching {
    val chain = BaseChain.getChain(baseChain)
    val response = BalanceGrpcTask(app, chain, address).run()
    if (response.resultData is ArrayList<*>) {
      val balance = response.resultData as ArrayList<CoinOuterClass.Coin>
      Either.Right(balance.map { it.amount.toLong() to it.denom })
    } else {
      Either.Left(Unit)
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Unit)

  suspend fun fetchDelegations(account: Account): Either<Unit, Unit> =
    kotlin.runCatching {
      DelegationsGrpcTask(app, BaseChain.getChain(account.baseChain), account).run()
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
      UnDelegationsGrpcTask(app, BaseChain.getChain(account.baseChain), account).run()
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
      AllRewardGrpcTask(app, BaseChain.getChain(account.baseChain), account).run()
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
          async(start = CoroutineStart.LAZY) { fetchNodeInfo(account) },
          async(start = CoroutineStart.LAZY) { fetchAuthorization(account) },
          async(start = CoroutineStart.LAZY) { fetchBondedValidators(account) },
          async(start = CoroutineStart.LAZY) { fetchUnbondedValidators(account) },
          async(start = CoroutineStart.LAZY) { fetchUnbondingValidators(account) },
          async(start = CoroutineStart.LAZY) { fetchBalance(account) },
          async(start = CoroutineStart.LAZY) { fetchDelegations(account) },
          async(start = CoroutineStart.LAZY) { fetchUnboundingDelegations(account) },
          async(start = CoroutineStart.LAZY) { fetchRewards(account) },
        ).awaitAll().let { results ->
          if (results.any { it.isLeft }) {
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
}
