package co.uk.basedapps.ui_server.logs

import android.content.Context
import android.util.Log
import co.uk.basedapps.domain.exception.NonFatalException
import co.uk.basedapps.domain.extension.isMainProcess
import co.uk.basedapps.domain.extension.takeIfNotBlank
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

/**
 * Must not be initialized on non-main process!
 */
@Singleton
class NonFatalReportTree @Inject constructor(
  @ApplicationContext private val context: Context,
) : Timber.Tree() {

  private val isMainProcess by lazy { context.isMainProcess() }
  private val crashlytics by lazy { FirebaseCrashlytics.getInstance() }

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (!isMainProcess) return
    if (priority != Log.ERROR) return
    val nonFatalException = t as? NonFatalException ?: return

    nonFatalException.message?.takeIfNotBlank()?.let { crashlytics.log(it) }
    val actualException = nonFatalException.cause ?: nonFatalException
    crashlytics.recordException(actualException)
  }
}
