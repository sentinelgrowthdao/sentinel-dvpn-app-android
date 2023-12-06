package co.uk.basedapps.vpn.server.routers

import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.domain.functional.requireRight
import co.uk.basedapps.vpn.server.error.HttpError
import co.uk.basedapps.vpn.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.vpn.server.models.EndpointModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.routeCommon(
  repository: HubRemoteRepository,
) {

  routing {
    post("/api/blockchain/endpoint") {
      val request = kotlin.runCatching {
        call.receive<EndpointModel>()
      }.getOrNull() ?: let {
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      ChannelBuilder.createCustomChannel(request.host, request.port)
      call.respond(HttpStatusCode.OK)
    }

    get("/api/blockchain/nodes") {
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      val result = repository.fetchNodesJson(offset = offset, limit = limit)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }

    get("/api/blockchain/plans") {
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      val result = repository.fetchPlansJson(offset = offset, limit = limit)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }

    get("/api/blockchain/plans/{planId}/nodes") {
      val planId = call.parameters["planId"]?.toLongOrNull()
        ?: return@get call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      val result = repository.fetchNodesForPlanJson(planId = planId, offset = offset, limit = limit)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }
  }
}
