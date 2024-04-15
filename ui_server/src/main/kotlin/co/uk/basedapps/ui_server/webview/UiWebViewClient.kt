package co.uk.basedapps.ui_server.webview

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UiWebViewClient(
  private val scope: CoroutineScope,
  private val isLoaded: () -> Unit,
  private val reload: () -> Unit,
) : WebViewClient() {

  companion object {
    const val ReloadIntervalMs = 100L
  }

  private var isError = false
  override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
    isError = true
  }

  override fun onPageFinished(view: WebView?, url: String?) {
    if (isError) {
      isError = false
      scope.launch(Dispatchers.Main) {
        delay(ReloadIntervalMs)
        reload()
      }
    } else {
      if (view?.progress == 100) {
        isLoaded()
      }
    }
  }
}
