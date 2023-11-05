package co.uk.basedapps.vpn.server

import co.uk.basedapps.vpn.common.provider.AppDetailsProvider
import co.uk.basedapps.vpn.network.repository.BasedRepository
import co.uk.basedapps.vpn.server.routers.routeDns
import co.uk.basedapps.vpn.server.routers.routeProxy
import co.uk.basedapps.vpn.server.routers.routeRegistry
import co.uk.basedapps.vpn.storage.BasedStorage
import co.uk.basedapps.vpn.vpn.DdsConfigurator
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.gson.GsonWebsocketContentConverter
import io.ktor.serialization.gson.gson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.path
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import java.time.Duration
import javax.inject.Inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.event.Level

class CoreServer
@Inject
constructor(
  private val provider: AppDetailsProvider,
  private val dnsConfigurator: DdsConfigurator,
  private val storage: BasedStorage,
  private val repository: BasedRepository,
) {

  fun init() {
    GlobalScope.launch {
      embeddedServer(Netty, provider.getServerPort(), "127.0.0.1") {
        configureCors()
        configureSockets()
        configureRouting()
        configureSerialization()
        configureMonitoring()
      }.start(wait = true)
    }
  }

  private fun Application.configureCors() {
    install(CORS) {
      exposeHeader(HttpHeaders.AccessControlAllowOrigin)
      exposeHeader(HttpHeaders.ContentType)
      allowHeader(HttpHeaders.AccessControlAllowOrigin)
      allowHeader(HttpHeaders.ContentType)
      allowMethod(HttpMethod.Put)
      allowMethod(HttpMethod.Delete)
      anyHost()
    }
  }

  private fun Application.configureSockets() {
    install(WebSockets) {
      contentConverter = GsonWebsocketContentConverter()
      pingPeriod = Duration.ofSeconds(15)
      timeout = Duration.ofSeconds(15)
      maxFrameSize = Long.MAX_VALUE
      masking = false
    }
  }

  private fun Application.configureRouting() {
    routeDns(dnsConfigurator)
    routeRegistry(storage)
    routeProxy(repository)
  }

  private fun Application.configureSerialization() {
    install(ContentNegotiation) {
      gson {
        setPrettyPrinting()
      }
    }
  }

  private fun Application.configureMonitoring() {
    install(CallLogging) {
      level = Level.INFO
      filter { call -> call.request.path().startsWith("/") }
    }
  }
}
