package co.uk.basedapps.vpn.server

import co.sentinel.cosmos.WalletRepository
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.vpn.common.provider.AppDetailsProvider
import co.uk.basedapps.vpn.network.repository.BasedRepository
import co.uk.basedapps.vpn.server.routers.routeCommon
import co.uk.basedapps.vpn.server.routers.routeDns
import co.uk.basedapps.vpn.server.routers.routeProxy
import co.uk.basedapps.vpn.server.routers.routeRegistry
import co.uk.basedapps.vpn.server.routers.routeStatic
import co.uk.basedapps.vpn.server.routers.routeVpn
import co.uk.basedapps.vpn.server.routers.routeWallet
import co.uk.basedapps.vpn.storage.BasedStorage
import co.uk.basedapps.vpn.vpn.DdsConfigurator
import co.uk.basedapps.vpn.vpn.VPNConnector
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
  private val vpnConnector: VPNConnector,
  private val walletRepository: WalletRepository,
  private val hubRepository: HubRemoteRepository,
) {

  fun init() {
    GlobalScope.launch {
      embeddedServer(Netty, provider.getServerPort(), "127.0.0.1") {
        configureMonitoring()
        configureCors()
        configureSockets()
        configureRouting()
        configureSerialization()
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
    routeStatic()
    routeDns(dnsConfigurator)
    routeRegistry(storage)
    routeProxy(repository)
    routeVpn(vpnConnector)
    routeWallet(walletRepository, hubRepository)
    routeCommon(hubRepository)
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
    }
  }
}
