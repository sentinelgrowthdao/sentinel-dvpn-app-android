package co.uk.basedapps.vpn.server.routers

import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.functional.requireRight
import co.uk.basedapps.vpn.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.vpn.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.vpn.server.error.HttpError.Companion.notFound
import co.uk.basedapps.vpn.server.models.RestoreWalletRequest
import co.uk.basedapps.vpn.server.models.WalletResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.routeWallet(
  walletRepository: WalletRepository,
  hubRepository: HubRemoteRepository,
) {

  routing {
    get("/api/blockchain/wallet") {
      val address = walletRepository.getAccountAddress()
      if (address != null) {
        val response = WalletResponse(address)
        call.respond(HttpStatusCode.OK, response)
      } else {
        call.respond(HttpStatusCode.NotFound, notFound)
      }
    }

    post("/api/blockchain/wallet") {
      val request = kotlin.runCatching {
        call.receive<RestoreWalletRequest>()
      }.getOrNull() ?: let {
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      walletRepository.clearWallet()
      val result = walletRepository.restoreAccount(request.mnemonic)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK)
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }

    delete("/api/blockchain/wallet") {
      walletRepository.clearWallet()
      call.respond(HttpStatusCode.OK)
    }

    get("/api/blockchain/wallet/{address}/balance") {
      val address = call.parameters["address"]
        ?: return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      val result = walletRepository.getBalancesByAddressJson(address = address)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        call.respond(HttpStatusCode.NotFound, notFound)
      }
    }

    get("/api/blockchain/wallet/{address}/subscriptions") {
      val address = call.parameters["address"]
        ?: return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      val result = hubRepository.fetchSubscriptionsForAddressJson(
        address = address,
        offset = offset,
        limit = limit,
      )
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        call.respond(HttpStatusCode.NotFound, notFound)
      }
    }
  }
}
