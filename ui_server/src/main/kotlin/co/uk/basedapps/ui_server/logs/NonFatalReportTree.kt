package co.uk.basedapps.ui_server.logs

import android.util.Log
import co.uk.basedapps.domain.exception.NonFatalException
import co.uk.basedapps.domain.extension.takeIfNotBlank
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class NonFatalReportTree @Inject constructor() : Timber.Tree() {

  private val crashlytics = FirebaseCrashlytics.getInstance()

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (priority != Log.ERROR) return
    val nonFatalException = t as? NonFatalException ?: return

    nonFatalException.message?.takeIfNotBlank()?.let { crashlytics.log(it) }
    val actualException = nonFatalException.cause ?: nonFatalException
    crashlytics.recordException(actualException)
  }
}
