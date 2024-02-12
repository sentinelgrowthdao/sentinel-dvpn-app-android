package co.uk.basedapps.ui_server.logs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class FileLogTree @Inject constructor(
  @ApplicationContext private val context: Context,
) : Timber.Tree() {

  companion object {
    private const val ANDROID_LOG_TIME_FORMAT = "MM-dd-yy kk:mm:ss.SSS"
  }

  private val accumulatedLogs = CopyOnWriteArrayList<String>()
  private val dateFormat = SimpleDateFormat(ANDROID_LOG_TIME_FORMAT, Locale.US)

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    val date = System.currentTimeMillis().formatDate()
    accumulatedLogs.add(":> $date: [$tag] $message")
  }

  fun getLogsFile(): File? {
    val file = context.createFile("Logs.txt")
    return try {
      file.apply {
        bufferedWriter().use { buffer ->
          accumulatedLogs.forEach { line ->
            buffer.write(line)
          }
        }
      }
    } catch (e: Exception) {
      Timber.e(e)
      null
    }
  }

  private fun Long.formatDate(): String {
    val date = Date(this)
    return dateFormat.format(date)
  }

  private fun Context.createFile(fileName: String): File {
    val file = File(externalCacheDir, fileName)
    if (file.exists()) {
      file.delete()
    }
    return file
  }
}
