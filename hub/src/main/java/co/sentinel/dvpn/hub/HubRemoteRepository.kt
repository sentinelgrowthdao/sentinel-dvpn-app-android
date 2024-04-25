package co.sentinel.dvpn.hub

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import arrow.core.Either
import arrow.core.getOrElse
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.dvpn.hub.mapper.SessionMapper
import co.sentinel.dvpn.hub.mapper.SubscriptionMapper
import co.sentinel.dvpn.hub.model.NodeData
import co.sentinel.dvpn.hub.model.NodeInfo
import co.sentinel.dvpn.hub.model.Session
import co.sentinel.dvpn.hub.model.nodeSubscriptions
import co.sentinel.dvpn.hub.tasks.CancelNodeSubscription
import co.sentinel.dvpn.hub.tasks.CreateDirectTransaction
import co.sentinel.dvpn.hub.tasks.CreateNodeSubscription
import co.sentinel.dvpn.hub.tasks.CreatePlanSubscription
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
import co.sentinel.dvpn.hub.tasks.QueryPlans
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.models.KeyPair
import com.google.protobuf.util.JsonFormat
import com.google.protobuf2.Any
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import sentinel.node.v2.Querier.QueryNodesForPlanResponse
import sentinel.node.v2.Querier.QueryNodesResponse
import sentinel.plan.v2.Querier.QueryPlansResponse
import sentinel.subscription.v2.Subscription as GrpcSubscription
import sentinel.types.v1.StatusOuterClass
import timber.log.Timber

@Singleton
class HubRemoteRepository
@Inject constructor(
  private val app: BaseCosmosApp,
) {

  private val jsonFormatter = JsonFormat.printer()
    .includingDefaultValueFields()

  private suspend fun fetchPlans(
    offset: Long,
    limit: Long,
  ): Either<Failure, QueryPlansResponse> =
    QueryPlans(app).execute(offset = offset, limit = limit)

  suspend fun fetchPlansJson(
    offset: Long = 0,
    limit: Long = 10,
  ): Either<Failure, String> = fetchPlans(offset = offset, limit = limit)
    .map { result -> jsonFormatter.print(result) }

  private suspend fun fetchNodes(
    offset: Long,
    limit: Long,
  ): Either<Failure, QueryNodesResponse> =
    QueryNodes(app).execute(offset = offset, limit = limit)

  suspend fun fetchNodesJson(
    offset: Long = 0,
    limit: Long = 10,
  ): Either<Failure, String> = fetchNodes(offset = offset, limit = limit)
    .map { result -> jsonFormatter.print(result) }

  private suspend fun fetchNodesForPlan(
    planId: Long,
    offset: Long,
    limit: Long,
  ): Either<Failure, QueryNodesForPlanResponse> =
    QueryNodes(app).execute(planId = planId, offset = offset, limit = limit)

  suspend fun fetchNodesForPlanJson(
    planId: Long,
    offset: Long = 0,
    limit: Long = 10,
  ): Either<Failure, String> = fetchNodesForPlan(planId = planId, offset = offset, limit = limit)
    .map { result -> jsonFormatter.print(result) }

  suspend fun fetchNodesWithDetails(
    offset: Long = 0,
    limit: Long = 10,
  ) = kotlin.runCatching {
    supervisorScope {
      val queryResult = fetchNodes(offset = offset, limit = limit)
      val nodeInfoResult = async {
        FetchNodeInfo.execute(
          queryResult.getOrNull()?.nodesList.orEmpty(),
        )
      }
      val subscriptions = async {
        fetchSubscriptions().getOrNull()
          ?.nodeSubscriptions()
          ?.filter { it.nodeAddress.isNotEmpty() }
          ?: listOf()
      }

      nodeInfoResult.await()
        .mapNotNull { fixNodeInfoPrice(it) }
        .map { nodeInfo ->
          NodeData(
            nodeInfo = nodeInfo,
            subscription = subscriptions.await()
              .filter { nodeInfo.address == it.nodeAddress }
              .maxByOrNull { it.base.id },
          )
        }
        .let { Either.Right(it) }
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
        fetchSubscriptions().getOrNull()
          ?.nodeSubscriptions()
          ?: listOf()
      withContext(Dispatchers.Default) {
        val subscribedNodesResponse =
          subscriptions.map { subscription ->
            async(start = CoroutineStart.LAZY) {
              fetchNode(
                subscription.nodeAddress,
              )
            }
          }.awaitAll()

        val nodeInfoResult =
          FetchNodeInfo.execute(
            subscribedNodesResponse.mapNotNull { it.getOrNull() },
          )
        nodeInfoResult.mapNotNull {
          fixNodeInfoPrice(it)
        }.map { nodeInfo ->
          NodeData(
            nodeInfo = nodeInfo,
            subscription = subscriptions.filter { nodeInfo.address == it.nodeAddress }
              .maxByOrNull { it.base.id },
          )
        }.let {
          Either.Right(it)
        }
      }
    }.onFailure { Timber.e(it) }
      .getOrNull() ?: Either.Left(Failure.AppError)
  }

  suspend fun fetchNode(nodeAddress: String) = QueryNode(app).execute(nodeAddress)

  suspend fun fetchNodeDataWithoutSubscription(nodeAddress: String): Either<Failure, NodeData> {
    val fetchNodeResult = fetchNode(nodeAddress)
    val node = fetchNodeResult.getOrNull() ?: return Either.Left(Failure.AppError)
    val nodeInfo = FetchNodeInfo.execute(listOf(node))
      .firstOrNull() ?: return Either.Left(Failure.AppError)
    val fixedNodeData = fixNodeInfoPrice(nodeInfo) ?: return Either.Left(Failure.AppError)
    return Either.Right(NodeData(nodeInfo = fixedNodeData, subscription = null))
  }

  fun generateNodeSubscriptionPayload(
    nodeAddress: String,
    denom: String,
    gigabytes: Long,
    hours: Long,
  ): Either<Failure, Any> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val result = CreateNodeSubscription.execute(
      walletAddress = account.address,
      nodeAddress = nodeAddress,
      denom = denom,
      gigabytes = gigabytes,
      hours = hours,
    )
    Either.Right(result)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  fun generatePlanSubscriptionPayload(
    planId: Long,
    denom: String,
  ): Either<Failure, Any> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val result = CreatePlanSubscription.execute(
      walletAddress = account.address,
      planId = planId,
      denom = denom,
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
    val activeSessions = FetchSessions(app).execute(account.address)
      .getOrElse { listOf() }
      .filter { subscriptionIds.contains(it.subscriptionId) }
      .filter { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
      .map { it.id }
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
    val result = FetchSubscription(app).execute(subscriptionId)
    result.getOrNull()
      ?.let(SubscriptionMapper::map)
      ?.let { Either.Right(it) }
      ?: Either.Left(Failure.AppError)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchSubscriptions() = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val subscriptions = fetchSubscriptionsForAddress(
      address = account.address,
      offset = 0,
      limit = Long.MAX_VALUE,
    )
    subscriptions.map { response ->
      response.subscriptionsList
        .mapNotNull(SubscriptionMapper::map)
    }
  }.onFailure {
    Timber.e(it)
  }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun fetchSubscriptionsForAddressJson(
    address: String,
    offset: Long,
    limit: Long,
  ) = fetchSubscriptionsForAddress(address = address, offset = offset, limit = limit)
    .map { response ->
      val nodeSubscriptions = mutableListOf<GrpcSubscription.NodeSubscription>()
      val planSubscriptions = mutableListOf<GrpcSubscription.PlanSubscription>()
      response.subscriptionsList.forEach { subscription ->
        when {
          subscription.`is`(GrpcSubscription.NodeSubscription::class.java) ->
            subscription.unpack(GrpcSubscription.NodeSubscription::class.java)
              .also { nodeSubscriptions.add(it) }

          subscription.`is`(GrpcSubscription.PlanSubscription::class.java) ->
            subscription.unpack(GrpcSubscription.PlanSubscription::class.java)
              .also { planSubscriptions.add(it) }
        }
      }
      JSONObject().apply {
        put("nodeSubscriptions", JSONArray(nodeSubscriptions.map { JSONObject(jsonFormatter.print(it)) }))
        put("planSubscriptions", JSONArray(planSubscriptions.map { JSONObject(jsonFormatter.print(it)) }))
      }.toString()
    }

  private suspend fun fetchSubscriptionsForAddress(
    address: String,
    offset: Long,
    limit: Long,
  ) = FetchSubscriptions(app).execute(address = address, offset = offset, limit = limit)

  fun generateDirectTransferPayload(
    recipientAddress: String,
    amount: String,
    denom: String,
  ): Either<Failure, Any> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val result = CreateDirectTransaction.execute(
      walletAddress = account.address,
      recipientAddress = recipientAddress,
      amount = amount,
      denom = denom,
    )
    Either.Right(result)
  }.onFailure { Timber.e(it) }
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

  fun resetConnection() {}

  fun clearData() {
    app.baseDao.clearDB()
  }

  suspend fun generateConnectToNodeMessages(
    subscriptionId: Long,
    nodeAddress: String,
  ): Either<Failure, List<Any>> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val sessions = FetchSessions(app).execute(account.address)
    sessions.map { response ->
      val activeSessions = response
        .filter { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
        .map { it.id }
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
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  fun generateConnectToNodeMessages(
    subscriptionId: Long,
    nodeAddress: String,
    activeSessionId: Long?,
  ): Either<Failure, List<Any>> = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val activeSessions = activeSessionId?.let(::listOf) ?: emptyList()
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
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  private suspend fun loadActiveSessionForAccount(address: String) = kotlin.runCatching {
    FetchSessions(app).execute(address)
      .getOrElse { listOf() }
      .firstOrNull { it.status == StatusOuterClass.Status.STATUS_ACTIVE }
      ?.let { Either.Right(it) }
      ?: Either.Left(Failure.NotFound)
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)

  suspend fun loadActiveSession(): Either<Failure, Session> {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    return loadActiveSessionForAccount(account.address)
      .map { SessionMapper.map(it) }
  }

  suspend fun loadActiveSessionForAccountJson(
    address: String,
  ): Either<Failure, String> =
    loadActiveSessionForAccount(address)
      .map { result -> jsonFormatter.print(result) }

  suspend fun fetchVpnProfile(
    session: Session,
    keyPair: KeyPair,
    signature: String,
  ) = kotlin.runCatching {
    val account = app.baseDao.onSelectAccount(app.baseDao.lastUser)
      ?: return Either.Left(Failure.AppError)
    val nodeResult = fetchNode(session.node)
    nodeResult.map { response ->
      FetchVpnProfile.execute(
        account = account,
        remoteUrl = response.remoteUrl,
        sessionId = session.id,
        keyPair = keyPair,
        signature = signature,
      )
    }
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: Either.Left(Failure.AppError)
}
