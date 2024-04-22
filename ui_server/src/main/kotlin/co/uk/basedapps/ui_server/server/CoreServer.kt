package co.uk.basedapps.ui_server.server

import co.sentinel.cosmos.WalletRepository
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.dvpn.hub.HubRemoteRepository
import co.uk.basedapps.blockchain.transaction.TransactionManager
import co.uk.basedapps.ui_server.BuildConfig
import co.uk.basedapps.ui_server.common.clipboard.ClipboardManager
import co.uk.basedapps.ui_server.common.provider.AppDetailsProvider
import co.uk.basedapps.ui_server.network.repository.BasedRepository
import co.uk.basedapps.ui_server.server.error.HttpError
import co.uk.basedapps.ui_server.server.routers.routeCommon
import co.uk.basedapps.ui_server.server.routers.routeDns
import co.uk.basedapps.ui_server.server.routers.routeProxy
import co.uk.basedapps.ui_server.server.routers.routeRegistry
import co.uk.basedapps.ui_server.server.routers.routeStatic
import co.uk.basedapps.ui_server.server.routers.routeTransaction
import co.uk.basedapps.ui_server.server.routers.routeVpn
import co.uk.basedapps.ui_server.server.routers.routeWallet
import co.uk.basedapps.ui_server.server.utils.EventBus
import co.uk.basedapps.ui_server.storage.BasedStorage
import co.uk.basedapps.ui_server.vpn.VPNConnector
import co.uk.basedapps.ui_server.vpn.VPNProfileFetcher
import co.uk.basedapps.ui_server.vpn.dns.DdsConfigurator
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.gson
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.util.logging.KtorSimpleLogger
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.event.Level

@Singleton
class CoreServer @Inject constructor(
  private val provider: AppDetailsProvider,
  private val dnsConfigurator: DdsConfigurator,
  private val storage: BasedStorage,
  private val restRepository: BasedRepository,
  private val vpnConnector: VPNConnector,
  private val walletRepository: WalletRepository,
  private val hubRepository: HubRemoteRepository,
  private val transactionManager: TransactionManager,
  private val profileFetcher: VPNProfileFetcher,
  private val eventBus: EventBus,
  private val clipboard: ClipboardManager,
  private val cosmosApp: BaseCosmosApp,
) {

  private var isRunning = false

  fun init() {
    if (isRunning) return
    isRunning = true
    GlobalScope.launch {
      embeddedServer(Netty, provider.getServerPort(), provider.getServerHost()) {
        configureCors()
        configureSecureHeader()
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

  private fun Application.configureRouting() {
    routeStatic()
    routeDns(dnsConfigurator)
    routeRegistry(storage, provider, clipboard, eventBus)
    routeProxy(restRepository, eventBus)
    routeVpn(vpnConnector)
    routeWallet(walletRepository, hubRepository, profileFetcher)
    routeCommon(cosmosApp.channelBuilder, hubRepository)
    routeTransaction(transactionManager)
  }

  private fun Application.configureSecureHeader() {
    intercept(ApplicationCallPipeline.Plugins) {
      if (call.request.path().startsWith("/api") &&
        call.request.headers["x-key"] != BuildConfig.SECURITY_TOKEN
      ) {
        call.respond(HttpStatusCode.Forbidden, HttpError.accessDenied)
        return@intercept finish()
      }
    }
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
      logger = KtorSimpleLogger("ServerLog")
      format { call ->
        val status = call.response.status()
        val httpMethod = call.request.httpMethod.value
        val path = call.request.path()
        "REQ: $status, $httpMethod, $path"
      }
      filter { call -> call.request.path().startsWith("/api") }
    }
  }
}
