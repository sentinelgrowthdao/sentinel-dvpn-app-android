package co.uk.basedapps.domain.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
  /**
   * Generic network connection error. For more specific ones, extend [FeatureFailure].
   */
  object NetworkConnection : Failure()

  object ServerError : Failure()

  object ApiError : Failure()

  object AppError : Failure()

  object NotFound : Failure()

  object InsufficientFunds : Failure()

  abstract class FeatureFailure : Failure()
}
