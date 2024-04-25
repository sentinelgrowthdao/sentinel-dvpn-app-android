package co.uk.basedapps.ui_server.server.routers

import android.webkit.URLUtil
import co.uk.basedapps.ui_server.logs.OpenBrowserEvent
import co.uk.basedapps.ui_server.network.repository.BasedRepository
import co.uk.basedapps.ui_server.server.error.HttpError
import co.uk.basedapps.ui_server.server.error.HttpError.Companion.badRequest
import co.uk.basedapps.ui_server.server.models.BrowserProxyRequest
import co.uk.basedapps.ui_server.server.utils.EventBus
import co.uk.basedapps.ui_server.server.utils.isValidMailTo
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
import timber.log.Timber

private const val ProxyPath = "api/proxy/"

fun Application.routeProxy(
  repository: BasedRepository,
  eventBus: EventBus,
) {

  routing {
    get("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val queries = call.getQueries()

      repository.getProxy(path, headers, queries)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft {
          val error = it.parseError()
          call.respond(status = error.first, message = error.second)
        }
    }

    post("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val body = try {
        call.receive<String>()
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      repository.postProxy(path, headers, body)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft {
          val error = it.parseError()
          call.respond(status = error.first, message = error.second)
        }
    }

    delete("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val queries = call.getQueries()

      repository.deleteProxy(path, headers, queries)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft {
          val error = it.parseError()
          call.respond(status = error.first, message = error.second)
        }
    }

    put("$ProxyPath{...}") {
      val path = call.getPath()
      val headers: Map<String, String> = call.getHeaders()
      val body = try {
        call.receive<String>()
      } catch (e: Exception) {
        Timber.e(e)
        return@put call.respond(HttpStatusCode.BadRequest, badRequest)
      }

      repository.putProxy(path, headers, body)
        .onRight { call.respond(HttpStatusCode.OK, it) }
        .onLeft {
          val error = it.parseError()
          call.respond(status = error.first, message = error.second)
        }
    }

    post("$ProxyPath/browser") {
      val url = try {
        call.receive<BrowserProxyRequest>().url
      } catch (e: Exception) {
        Timber.e(e)
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      if (!URLUtil.isValidUrl(url) && !url.isValidMailTo()) {
        Timber.e("Url is not valid")
        return@post call.respond(HttpStatusCode.BadRequest, badRequest)
      }
      eventBus.emitEvent(OpenBrowserEvent(url))
      call.respond(HttpStatusCode.OK)
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
    .filter { it.key != "x-key" }
    as Map<String, String>
}

private fun ApplicationCall.getQueries(): Map<String, String> {
  return request.queryParameters.entries()
    .associate { it.key to it.value.firstOrNull() }
    as Map<String, String>
}

private fun Exception.parseError(): Pair<HttpStatusCode, Any> {
  return when (this) {
    is HttpException ->
      HttpStatusCode.fromValue(this.code()) to
        (this.response()?.errorBody()?.string() ?: this.message())

    else -> HttpStatusCode.InternalServerError to HttpError.internalServer
  }
}
