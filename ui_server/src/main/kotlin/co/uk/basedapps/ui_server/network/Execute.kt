package co.uk.basedapps.ui_server.network

import co.uk.basedapps.domain.functional.Either
import timber.log.Timber

typealias NetResult<T> = Either<Exception, T>

suspend fun <T> execute(
  method: suspend () -> T,
): NetResult<T> {
  return try {
    Either.Right(method.invoke())
  } catch (e: Exception) {
    Timber.e(e)
    Either.Left(e)
  }
}
