package co.sentinel.dvpn.hub

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import co.sentinel.cosmos.base.BaseChain
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.dvpn.hub.mapper.SessionMapper
import co.sentinel.dvpn.hub.mapper.SubscriptionMapper
import co.sentinel.dvpn.hub.model.NodeData
import co.sentinel.dvpn.hub.model.NodeInfo
import co.sentinel.dvpn.hub.model.Session
import co.sentinel.dvpn.hub.tasks.CancelNodeSubscription
import co.sentinel.dvpn.hub.tasks.CreateNodeSubscription
import co.sentinel.dvpn.hub.tasks.FetchNodeInfo
import co.sentinel.dvpn.hub.tasks.FetchSessions
import co.sentinel.dvpn.hub.tasks.FetchSubscription
import co.sentinel.dvpn.hub.tasks.FetchSubscriptions
import co.sentinel.dvpn.hub.tasks.FetchVpnProfile
import co.sentinel.dvpn.hub.tasks.GenerateStartActiveSessionMessage
import co.sentinel.dvpn.hub.tasks.GenerateStopActiveSessionMessages
import co.sentinel.dvpn.hub.tasks.GetOwnIP
import co.sentinel.dvpn.hub.tasks.QueryNode
import co.sentinel.dvpn.hub.tasks.QueryNodes
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.functional.getOrElse
import co.uk.basedapps.domain.functional.getOrNull
import co.uk.basedapps.domain.functional.requireLeft
import co.uk.basedapps.domain.functional.requireRight
import co.uk.basedapps.domain.models.KeyPair
import com.google.protobuf2.Any
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import sentinel.types.v1.StatusOuterClass
import timber.log.Timber

@Singleton
class HubRemoteRepository
@Inject constructor(
  private val app: BaseCosmosApp,
) {

  suspend fun fetchNodes(offset: Long, limit: Long) = kotlin.runCatching {
    supervisorScope {
      val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
        ?: return@supervisorScope Either.Left(Failure.AppError)
      val queryResult = QueryNodes.execute(
        chain = BaseChain.getChain(account.baseChain),
        offset = offset,
        limit = limit,
      )
      val nodeInfoResult = async { FetchNodeInfo.execute(queryResult.getOrElse(listOf())) }
      val subscriptions =
        async { fetchSubscriptions().getOrElse(null) ?: listOf() }

      nodeInfoResult.await().mapNotNull {
        fixNodeInfoPrice(it)
      }.map { nodeInfo ->
        NodeData(
          nodeInfo = nodeInfo,
          subscription = subscriptions.await().filter { nodeInfo.address == it.node }
            .maxByOrNull { it.id },
        )
      }.let {
        Either.Right(it)
      }
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchNodes(): Either<Failure, List<NodeData>> = kotlin.runCatching {
    supervisorScope {
      val queryResult = QueryNodes.execute(chain = BaseChain.SENTINEL_MAIN)
      val nodeInfoResult = async { FetchNodeInfo.execute(queryResult.getOrElse(listOf())) }
      val subscriptions =
        async {
          fetchSubscriptions().getOrElse(null) ?: listOf()
        }
      nodeInfoResult.await().mapNotNull {
        fixNodeInfoPrice(it)
      }.map { nodeInfo ->
        NodeData(
          nodeInfo = nodeInfo,
          subscription = subscriptions.await().filter { nodeInfo.address == it.node }
            .maxByOrNull { it.id },
        )
      }.let {
        Either.Right(it)
      }
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  /**
   * If node has a price in udvpn, return it, only with the udvpn price.
   * If it doesn't have a price, or none of the prices are in udvpn, return null so it can be discarded.
   */
  private fun fixNodeInfoPrice(nodeInfo: NodeInfo): NodeInfo? {
    val regex = "(\\d+)udvpn".toRegex()
    val matchResult = regex.find(nodeInfo.price)
    return matchResult?.let {
      nodeInfo.copy(price = it.value)
    }
  }

  suspend fun fetchSubscribedNodes(): Either<Failure, List<NodeData>> {
    return kotlin.runCatching {
      val subscriptions =
        fetchSubscriptions().getOrElse(null)
          ?.filter { it.node.isNotEmpty() } ?: listOf()
      withContext(Dispatchers.Default) {
        val subscribedNodesResponse =
          subscriptions.map { sub ->
            async(start = CoroutineStart.LAZY) {
              fetchNode(
                sub.node,
              )
            }
          }.awaitAll()

        val nodeInfoResult =
          FetchNodeInfo.execute(
            subscribedNodesResponse.filter { it.isRight }
              .map { it.requireRight() },
          )
        nodeInfoResult.mapNotNull {
          fixNodeInfoPrice(it)
        }.map { nodeInfo ->
          NodeData(
            nodeInfo = nodeInfo,
            subscription = subscriptions.filter { nodeInfo.address == it.node }
              .maxByOrNull { it.id },
          )
        }.let {
          Either.Right(it)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Failure.AppError)
  }

  suspend fun fetchNode(nodeAddress: String) = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    QueryNode.execute(nodeAddress, BaseChain.getChain(account.baseChain))
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchNodeDataWithoutSubscription(nodeAddress: String): Either<Failure, NodeData> {
    val fetchNodeResult = fetchNode(nodeAddress)
    if (fetchNodeResult.isLeft) return Either.Left(Failure.AppError)
    val node = fetchNodeResult.requireRight()
    val nodeInfo = FetchNodeInfo.execute(listOf(node))
      .firstOrNull() ?: return Either.Left(Failure.AppError)
    val fixedNodeData = fixNodeInfoPrice(nodeInfo) ?: return Either.Left(Failure.AppError)
    return Either.Right(NodeData(nodeInfo = fixedNodeData, subscription = null))
  }

  fun generateSubscribePayload(
    address: String,
    denom: String,
    gigabytes: Int,
  ): Either<Failure, Any> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val result = CreateNodeSubscription.execute(
      account = account,
      address = address,
      denom = denom,
      gigabytes = gigabytes.toLong(),
    )
    Either.Right(result)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun generateCancelPayload(
    subscriptionIds: List<Long>,
  ): Either<Failure, List<Any>> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    // fetch active sessions and stop them to successfully cancel subscription
    val activeSessions = FetchSessions.execute(account)
      .getOrElse(listOf())
      .filter { subscriptionIds.contains(it.subscriptionId) }
      .filter { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
    val stopMessages = GenerateStopActiveSessionMessages.execute(account, activeSessions)
    val result = stopMessages.toMutableList().apply {
      subscriptionIds.map { subscriptionId ->
        add(
          CancelNodeSubscription.execute(
            account,
            subscriptionId,
          ),
        )
      }
    }
    Either.Right(result)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchSubscription(subscriptionId: Long) = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val result = FetchSubscription.execute(account, subscriptionId)
    result.getOrNull()
      ?.let(SubscriptionMapper::map)
      ?.let { Either.Right(it) }
      ?: Either.Left(Failure.AppError)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchSubscriptions() = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val subscriptions = FetchSubscriptions.execute(account)
    if (subscriptions.isRight) {
      Either.Right(
        subscriptions.requireRight()
          .mapNotNull(SubscriptionMapper::map),
      )
    } else {
      Either.Left(Failure.AppError)
    }
  }.onFailure {
    Timber.e(it)
  }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchOwnIp() = GetOwnIP.execute().let {
    it.runCatching {
      ifEmpty {
        (app.context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).let {
          Formatter.formatIpAddress(it.connectionInfo.ipAddress)
        }
      }
    }.onFailure { Timber.e(it) }.getOrNull() ?: ""
  }

  fun resetConnectionBackoff() {
    // todo replace with a better solution, hopefully grpc supported like resetConnectionBackoff
    ChannelBuilder.resetSentinelMain()
  }

  fun clearData() {
    app.baseDao.clearDB()
  }

  suspend fun generateConnectToNodeMessages(
    subscriptionId: Long,
    nodeAddress: String,
  ): Either<Failure, List<Any>> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val sessions = FetchSessions.execute(account)
    if (sessions.isRight) {
      val activeSessions = sessions
        .requireRight()
        .filter { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
      val stopMessages = GenerateStopActiveSessionMessages.execute(account, activeSessions)
      return buildList<Any> {
        addAll(stopMessages)
        add(
          GenerateStartActiveSessionMessage.execute(
            account,
            subscriptionId,
            nodeAddress,
          ),
        )
      }.let { Either.Right(it) }
    } else {
      Either.Left(sessions.requireLeft())
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun loadActiveSession() = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    FetchSessions.execute(account)
      .getOrElse(listOf())
      .firstOrNull { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
      ?.let { Either.Right(SessionMapper.map(it)) }
      ?: Either.Left(Failure.AppError)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchVpnProfile(
    session: Session,
    keyPair: KeyPair,
    signature: String,
  ) = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val nodeResult = fetchNode(session.node)
    if (nodeResult.isRight) {
      FetchVpnProfile.execute(
        account = account,
        remoteUrl = nodeResult.requireRight().remoteUrl,
        sessionId = session.id,
        keyPair = keyPair,
        signature = signature,
      )
    } else {
      Either.Left(nodeResult.requireLeft())
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
