package co.sentinel.dvpn.hub.tasks

import co.sentinel.dvpn.hub.core.extensions.await
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

object GetOwnIP {
  private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(3, TimeUnit.SECONDS)
    .build()

  suspend fun execute() = kotlin.runCatching {
    loadUrl("https://api.ipify.org")?.let {
      val ipAddress = it.body?.string()
      it.body?.close()
      ipAddress
    } ?: ""
  }.onFailure { Timber.e(it) }
    .getOrNull() ?: ""

  private suspend fun loadUrl(url: String): Response? {
    var response: Response? = null
    try {
      response = client.newCall(Request.Builder().url(url).build()).await()
    } catch (e: IOException) {
      // Timber.e(e)
    }
    return response
  }
}
