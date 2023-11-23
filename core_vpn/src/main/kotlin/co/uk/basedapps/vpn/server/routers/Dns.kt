package co.uk.basedapps.vpn.server.routers

import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.vpn.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.vpn.server.error.HttpError.Companion.internalServer
import co.uk.basedapps.vpn.server.models.DnsListResponse
import co.uk.basedapps.vpn.server.models.DnsRequest
import co.uk.basedapps.vpn.server.models.DnsResponse
import co.uk.basedapps.vpn.vpn.DdsConfigurator
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.routing

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
      val request = kotlin.runCatching {
        call.receive<DnsRequest>()
      }.getOrNull() ?: let {
        return@put call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      val dns = DdsConfigurator.Dns.values().firstOrNull {
        it.name.equals(request.server, ignoreCase = true)
      } ?: return@put call.respond(HttpStatusCode.BadRequest, badRequest)

      return@put when (configurator.setDns(dns)) {
        is Either.Right -> call.respond(HttpStatusCode.OK)
        is Either.Left -> call.respond(HttpStatusCode.InternalServerError, internalServer)
      }
    }
  }
}
