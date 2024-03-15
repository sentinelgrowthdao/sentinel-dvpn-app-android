package co.uk.basedapps.ui_server.server.routers

import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.exception.Failure
import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.functional.requireLeft
import co.uk.basedapps.domain.functional.requireRight
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
      val result = walletRepository.generateKeywords()
      if (result.isRight) {
        val data = result.requireRight()
        val response = KeywordsResponse(data.keywords)
        call.respond(HttpStatusCode.OK, response)
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
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
      Timber.tag(VpnConnectTag).d("Fetch subscriptions:")
      val result = hubRepository.fetchSubscriptionsForAddressJson(
        address = address,
        offset = offset,
        limit = limit,
      )
      if (result.isRight) {
        Timber.tag(VpnConnectTag).d("Active subscriptions: ${result.requireRight()}")
        call.respond(HttpStatusCode.OK, result.requireRight())
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
          Timber.tag(VpnConnectTag).d("Active sessions: ${result.requireRight()}")
          call.respond(HttpStatusCode.OK, result.requireRight())
        }

        result.requireLeft() is Failure.NotFound -> {
          Timber.tag(VpnConnectTag).d("No active session")
          call.respond(HttpStatusCode.NotFound, notFound)
        }

        else -> {
          Timber.tag(VpnConnectTag).d("Failed to fetch active sessions")
          call.respond(HttpStatusCode.InternalServerError, internalServer)
        }
      }
    }

    post("/api/blockchain/wallet/connect") {
      val request = try {
        call.receive<CredentialsRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      Timber.tag(VpnConnectTag).d("Connect to node: $request")
      val credentialsRes = profileFetcher.fetch(request)
      if (credentialsRes.isRight) {
        call.respond(HttpStatusCode.OK, credentialsRes.requireRight())
      } else {
        return@post call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }
  }
}
