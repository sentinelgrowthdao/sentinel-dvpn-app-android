package co.uk.basedapps.ui_server.server.routers

import co.uk.basedapps.blockchain.transaction.TransactionManager
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.ui_server.server.error.HttpError
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.notFound
import co.uk.basedapps.ui_server.server.models.DirectPaymentRequest
import co.uk.basedapps.ui_server.server.models.NodeSubscriptionRequest
import co.uk.basedapps.ui_server.server.models.PlanSubscriptionRequest
import co.uk.basedapps.ui_server.server.models.SessionStartRequest
import co.uk.basedapps.ui_server.server.utils.VpnConnectTag
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import timber.log.Timber

fun Application.routeTransaction(
  transactionManager: TransactionManager,
) {

  routing {
    post("/api/blockchain/nodes/{nodeAddress}/subscription") {
      val nodeAddress = call.parameters["nodeAddress"]
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val granter = call.request.headers["x-fee-granter"]
      val request = try {
        call.receive<NodeSubscriptionRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        null
      }
      if (nodeAddress == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      Timber.tag(VpnConnectTag).d("Create node subscription $request")
      transactionManager.subscribeToNode(
        nodeAddress = nodeAddress,
        denom = request.denom,
        gigabytes = request.gigabytes,
        hours = request.hours,
        gasPrice = gasPrice,
        chainId = chainId,
        granter = granter,
      )
        .onRight {
          Timber.tag(VpnConnectTag).d("Subscription request broadcast")
          call.respond(HttpStatusCode.OK, it)
        }
        .onLeft {
          Timber.tag(VpnConnectTag).d("Subscription broadcast failed")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
    }

    post("/api/blockchain/plans/{planId}/subscription") {
      val planId = call.parameters["planId"]?.toLongOrNull()
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val granter = call.request.headers["x-fee-granter"]
      val request = try {
        call.receive<PlanSubscriptionRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        null
      }
      if (planId == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      Timber.tag(VpnConnectTag).d("Create node subscription $request")
      transactionManager.subscribeToPlan(
        planId = planId,
        denom = request.denom,
        gasPrice = gasPrice,
        chainId = chainId,
        granter = granter,
      )
        .onRight {
          Timber.tag(VpnConnectTag).d("Subscription request broadcast")
          call.respond(HttpStatusCode.OK, it)
        }
        .onLeft {
          Timber.tag(VpnConnectTag).d("Subscription broadcast failed")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
    }

    post("/api/blockchain/wallet/{address}/balance") {
      val recipientAddress = call.parameters["address"]
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val granter = call.request.headers["x-fee-granter"]
      val request = try {
        call.receive<DirectPaymentRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        null
      }
      if (recipientAddress == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      transactionManager.makeDirectTransfer(
        recipientAddress = recipientAddress,
        amount = request.amount,
        denom = request.denom,
        gasPrice = gasPrice,
        chainId = chainId,
        granter = granter,
      )
        .onRight {
          Timber.tag(VpnConnectTag).d("Transfer request broadcast")
          call.respond(HttpStatusCode.OK, it)
        }
        .onLeft {
          Timber.tag(VpnConnectTag).d("Transfer broadcast failed")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
    }

    post("/api/blockchain/wallet/{address}/session") {
      val recipientAddress = call.parameters["address"]
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val granter = call.request.headers["x-fee-granter"]
      val request = try {
        call.receive<SessionStartRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        null
      }
      if (recipientAddress == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      Timber.tag(VpnConnectTag).d(
        "Start new session on subscription (${request.subscriptionId}) " +
          "and node (${request.nodeAddress}). Stop old session (${request.activeSession})",
      )
      transactionManager.startSession(
        subscriptionId = request.subscriptionId,
        nodeAddress = request.nodeAddress,
        activeSession = request.activeSession,
        gasPrice = gasPrice,
        chainId = chainId,
        granter = granter,
      )
        .onRight {
          Timber.tag(VpnConnectTag).d("Session request broadcast")
          call.respond(HttpStatusCode.OK, it)
        }
        .onLeft {
          Timber.tag(VpnConnectTag).d("Session broadcast failed")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
    }

    get("/api/blockchain/transactions/{txHash}") {
      val txHash = call.parameters["txHash"]
      if (txHash.isNullOrBlank()) {
        return@get call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      transactionManager.fetchTransaction(txHash)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { failure ->
          when (failure) {
            Failure.NotFound -> call.respond(HttpStatusCode.NotFound, notFound)
            else -> call.respond(HttpStatusCode.InternalServerError, internalServer)
          }
        }
    }
  }
}
