package co.uk.basedapps.ui_server.server.routers

import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing

fun Application.routeStatic() {
  routing {
    staticResources("/", "")
  }
}
