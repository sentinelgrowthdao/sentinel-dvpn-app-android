package co.uk.basedapps.vpn.server.routers

import co.uk.basedapps.blockchain.subscription.TransactionManager
import co.uk.basedapps.vpn.server.error.HttpError
import co.uk.basedapps.vpn.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.vpn.server.models.DirectPaymentRequest
import co.uk.basedapps.vpn.server.models.NodeSubscriptionRequest
import co.uk.basedapps.vpn.server.models.PlanSubscriptionRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.routeTransaction(
  transactionManager: TransactionManager,
) {

  routing {
    post("/api/blockchain/nodes/{nodeAddress}/subscription") {
      val nodeAddress = call.parameters["nodeAddress"]
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val request = kotlin.runCatching { call.receive<NodeSubscriptionRequest>() }.getOrNull()
      if (nodeAddress == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      val result = transactionManager.subscribeToNode(
        nodeAddress = nodeAddress,
        denom = request.denom,
        gigabytes = request.gigabytes,
        hours = request.hours,
        gasPrice = gasPrice,
        chainId = chainId,
      )
      if (result.isRight) {
        call.respond(HttpStatusCode.OK)
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }

    post("/api/blockchain/plans/{planId}/subscription") {
      val planId = call.parameters["planId"]?.toLongOrNull()
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val request = kotlin.runCatching { call.receive<PlanSubscriptionRequest>() }.getOrNull()
      if (planId == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      val result = transactionManager.subscribeToPlan(
        planId = planId,
        denom = request.denom,
        providerAddress = request.providerAddress,
        gasPrice = gasPrice,
        chainId = chainId,
      )
      if (result.isRight) {
        call.respond(HttpStatusCode.OK)
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }

    post("/api/blockchain/wallet/{address}/balance") {
      val recipientAddress = call.parameters["address"]
      val gasPrice = call.request.headers["x-gas-prices"]?.toLongOrNull()
      val chainId = call.request.headers["x-chain-id"]
      val request = kotlin.runCatching { call.receive<DirectPaymentRequest>() }.getOrNull()
      if (recipientAddress == null || gasPrice == null || chainId == null || request == null) {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      val result = transactionManager.makeDirectTransfer(
        recipientAddress = recipientAddress,
        amount = request.amount,
        denom = request.denom,
        gasPrice = gasPrice,
        chainId = chainId,
      )
      if (result.isRight) {
        call.respond(HttpStatusCode.OK)
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }
  }
}
