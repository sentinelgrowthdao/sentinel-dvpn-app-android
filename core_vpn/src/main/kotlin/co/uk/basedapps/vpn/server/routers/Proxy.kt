package co.uk.basedapps.vpn.server.routers

import co.uk.basedapps.domain.functional.requireLeft
import co.uk.basedapps.domain.functional.requireRight
import co.uk.basedapps.vpn.network.NetResult
import co.uk.basedapps.vpn.network.repository.BasedRepository
import co.uk.basedapps.vpn.server.error.HttpError
import co.uk.basedapps.vpn.server.error.HttpError.Companion.badRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.path
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import retrofit2.HttpException

private const val ProxyPath = "api/proxy/"

fun Application.routeProxy(
  repository: BasedRepository,
) {

  routing {
    get("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val queries = call.getQueries()

      val result = repository.getProxy(path, headers, queries)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        val error = result.parseError()
        call.respond(status = error.first, message = error.second)
      }
    }

    post("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val body = kotlin.runCatching {
        call.receive<String>()
      }.getOrNull() ?: let {
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      val result = repository.postProxy(path, headers, body)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        val error = result.parseError()
        call.respond(status = error.first, message = error.second)
      }
    }

    delete("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val queries = call.getQueries()

      val result = repository.deleteProxy(path, headers, queries)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        val error = result.parseError()
        call.respond(status = error.first, message = error.second)
      }
    }

    put("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val body = kotlin.runCatching {
        call.receive<String>()
      }.getOrNull() ?: let {
        return@put call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      val result = repository.putProxy(path, headers, body)
      if (result.isRight) {
        call.respond(HttpStatusCode.OK, result.requireRight())
      } else {
        val error = result.parseError()
        call.respond(status = error.first, message = error.second)
      }
    }
  }
}

private fun ApplicationCall.getPath(): String {
  return request.path().replace("/$ProxyPath", "")
}

private fun ApplicationCall.getHeaders(): Map<String, String> {
  return request.headers.entries()
    .associate { it.key to it.value.firstOrNull() }
    .filter { it.key.startsWith("x-") && it.value != null }
    as Map<String, String>
}

private fun ApplicationCall.getQueries(): Map<String, String> {
  return request.queryParameters.entries()
    .associate { it.key to it.value.firstOrNull() }
    as Map<String, String>
}

private fun NetResult<String>.parseError(): Pair<HttpStatusCode, Any> {
  return when (val exception = requireLeft()) {
    is HttpException ->
      HttpStatusCode.fromValue(exception.code()) to
        (exception.response()?.errorBody()?.string() ?: exception.message())

    else -> HttpStatusCode.InternalServerError to HttpError.internalServer
  }
}
