package co.uk.basedapps.ui_server.server.routers

import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.ui_server.server.error.HttpError
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.ui_server.server.models.EndpointModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import timber.log.Timber

fun Application.routeCommon(
  channelBuilder: ChannelBuilder,
  repository: HubRemoteRepository,
) {

  routing {
    get("/api/blockchain/endpoint") {
      val endpoint = channelBuilder.getCurrentEndpoint()
      val response = EndpointModel(host = endpoint.first, port = endpoint.second)
      call.respond(HttpStatusCode.OK, response)
    }

    post("/api/blockchain/endpoint") {
      val request = try {
        call.receive<EndpointModel>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      }
      channelBuilder.setCustomEndpoint(request.host, request.port)
      call.respond(HttpStatusCode.OK)
    }

    get("/api/blockchain/nodes") {
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      repository.fetchNodesJson(offset = offset, limit = limit)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }

    get("/api/blockchain/plans") {
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      repository.fetchPlansJson(offset = offset, limit = limit)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }

    get("/api/blockchain/plans/{planId}/nodes") {
      val planId = call.parameters["planId"]?.toLongOrNull()
        ?: return@get call.respond(HttpStatusCode.BadRequest, HttpError.badRequest)
      val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0L
      val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 10L
      repository.fetchNodesForPlanJson(planId = planId, offset = offset, limit = limit)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft { call.respond(HttpStatusCode.InternalServerError, internalServer) }
    }
  }
}
