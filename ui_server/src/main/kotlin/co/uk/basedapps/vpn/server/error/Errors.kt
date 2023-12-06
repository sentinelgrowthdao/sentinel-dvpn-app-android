package co.uk.basedapps.vpn.server.error

import co.uk.basedapps.vpn.server.utils.FailureReasonSerializer
import com.google.gson.annotations.JsonAdapter
import io.ktor.http.HttpStatusCode

data class ErrorWrapper(
  val error: Error,
  val code: HttpStatusCode = HttpStatusCode.InternalServerError,
)

sealed class Error {
  data class GeneralError(
    val reason: String,
    val error: Boolean = true,
  ) : Error()

  data class InnerError(
    val error: Boolean = true,
    @JsonAdapter(FailureReasonSerializer::class)
    val reason: FailureReason,
  ) : Error() {
    data class FailureReason(
      val message: String,
      val code: Int,
    )
  }
}

sealed class HttpError {
  companion object {
    val badRequest = Error.GeneralError("Bad Request") // 400
    val unauthorized = Error.GeneralError("Unauthorized") // 401..402
    val accessDenied = Error.GeneralError("Access Denied") // 403
    val notFound = Error.GeneralError("Not Found") // 404
    val internalServer = Error.GeneralError("Internal Server Error") // 500
  }
}
