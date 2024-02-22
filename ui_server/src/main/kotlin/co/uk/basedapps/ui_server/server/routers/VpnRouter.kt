package co.uk.basedapps.ui_server.server.routers

import co.uk.basedapps.ui_server.network.model.Credentials
import co.uk.basedapps.ui_server.network.model.DataWrapper
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.ui_server.server.models.TunnelStatusResponse
import co.uk.basedapps.ui_server.vpn.VPNConnector
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import timber.log.Timber

fun Application.routeVpn(
  vpnConnector: VPNConnector,
) {

  routing {
    post("/api/connect") {
      val request = try {
        call.receive<DataWrapper<Credentials>>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      val result = vpnConnector.connect(request.data)
      call.respond(HttpStatusCode.OK, TunnelStatusResponse(result.isRight))
    }

    post("/api/disconnect") {
      vpnConnector.disconnect()
      call.respond(HttpStatusCode.OK, TunnelStatusResponse(false))
    }

    get("/api/status") {
      call.respond(HttpStatusCode.OK, TunnelStatusResponse(vpnConnector.isConnected()))
    }
  }
}
