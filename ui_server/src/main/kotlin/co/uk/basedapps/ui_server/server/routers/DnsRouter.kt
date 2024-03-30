package co.uk.basedapps.ui_server.server.routers

import co.uk.basedapps.ui_server.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.ui_server.server.models.DnsListResponse
import co.uk.basedapps.ui_server.server.models.DnsRequest
import co.uk.basedapps.ui_server.server.models.DnsResponse
import co.uk.basedapps.ui_server.vpn.DdsConfigurator
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import timber.log.Timber

fun Application.routeDns(
  configurator: DdsConfigurator,
) {
  routing {
    get("/api/dns/current") {
      val defaultDns = configurator.getDefaultDns()
      val response = DnsResponse(defaultDns.name, defaultDns.address)
      call.respond(HttpStatusCode.OK, response)
    }

    get("/api/dns/list") {
      val dnsList = configurator.getDnsList()
      val response = DnsListResponse(dnsList.map { dns -> DnsResponse(dns.name, dns.address) })
      call.respond(HttpStatusCode.OK, response)
    }

    put("/api/dns") {
      val request = try {
        call.receive<DnsRequest>()
      } catch (e: Exception) {
        Timber.e(e)
        return@put call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      val dns = DdsConfigurator.Dns.entries.firstOrNull {
        it.name.equals(request.server, ignoreCase = true)
      } ?: return@put call.respond(HttpStatusCode.BadRequest, badRequest)

      configurator.setDns(dns)
      call.respond(HttpStatusCode.OK)
    }
  }
}
