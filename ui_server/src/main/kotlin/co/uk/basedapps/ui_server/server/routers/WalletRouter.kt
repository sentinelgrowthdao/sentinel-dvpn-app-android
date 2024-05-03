package co.uk.basedapps.ui_server.server.routers

import arrow.core.Either
import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.notFound
import co.uk.basedapps.ui_server.server.models.CredentialsRequest
import co.uk.basedapps.ui_server.server.models.KeywordsResponse
import co.uk.basedapps.ui_server.server.models.RestoreWalletRequest
import co.uk.basedapps.ui_server.server.models.WalletResponse
import co.uk.basedapps.ui_server.server.utils.VpnConnectTag
import co.uk.basedapps.ui_server.vpn.VPNProfileFetcher
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import timber.log.Timber

fun Application.routeWallet(
  walletRepository: WalletRepository,
  hubRepository: HubRemoteRepository,
  profileFetcher: VPNProfileFetcher,
) {

  routing {
    get("/api/blockchain/keywords") {
      walletRepository.generateKeywords()
        .onRight { call.respond(HttpStatusCode.OK, KeywordsResponse(it.keywords)) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }

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
      val request = try {
        call.receive<RestoreWalletRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      walletRepository.clearWallet()
      walletRepository.restoreAccount(request.mnemonic)
        .onRight { call.respond(HttpStatusCode.OK) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }

    delete("/api/blockchain/wallet") {
      walletRepository.clearWallet()
      call.respond(HttpStatusCode.OK)
    }

    get("/api/blockchain/wallet/{address}/balance") {
      val address = call.parameters["address"]
        ?: return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      walletRepository.getBalancesByAddressJson(address = address)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { call.respond(HttpStatusCode.NotFound, notFound) }
    }

    get("/api/blockchain/wallet/{address}/subscriptions") {
      val address = call.parameters["address"]
        ?: return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      Timber.tag(VpnConnectTag).d("Fetch subscriptions:")
      val result = hubRepository.fetchSubscriptionsForAddressJson(
        address = address,
        offset = offset,
        limit = limit,
      )
      if (result.isRight()) {
        val subscriptions = result.getOrNull().orEmpty()
        Timber.tag(VpnConnectTag).d("Active subscriptions: $subscriptions")
        call.respond(HttpStatusCode.OK, subscriptions)
      } else {
        Timber.tag(VpnConnectTag).d("No active subscriptions")
        call.respond(HttpStatusCode.NotFound, notFound)
      }
    }

    get("/api/blockchain/wallet/{address}/session") {
      val address = call.parameters["address"]
        ?: return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      Timber.tag(VpnConnectTag).d("Fetch active sessions:")
      val result = hubRepository.loadActiveSessionForAccountJson(address)
      when {
        result is Either.Right -> {
          val session = result.getOrNull().orEmpty()
          Timber.tag(VpnConnectTag).d("Active sessions: $session")
          call.respond(HttpStatusCode.OK, session)
        }

        result.leftOrNull() is Failure.NotFound -> {
          Timber.tag(VpnConnectTag).d("No active session")
          call.respond(HttpStatusCode.NotFound, notFound)
        }

        else -> {
          Timber.tag(VpnConnectTag).d("Failed to fetch active sessions")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
      }
    }

    get("/api/blockchain/wallet/{address}/grants/{granter}") {
      val address = call.parameters["address"]
      val granter = call.parameters["granter"]
      if (address == null || granter == null) {
        return@get call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      hubRepository.fetchFeegrantAllowanceJson(
        grantee = address,
        granter = granter,
      )
        .onRight { allowance -> call.respond(HttpStatusCode.OK, allowance) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }

    post("/api/blockchain/wallet/connect") {
      val request = try {
        call.receive<CredentialsRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      Timber.tag(VpnConnectTag).d("Connect to node: $request")
      profileFetcher.fetch(request)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }
  }
}
