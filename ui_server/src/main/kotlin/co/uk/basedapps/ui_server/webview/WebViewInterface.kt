package co.uk.basedapps.ui_server.webview

import android.content.ClipboardManager
import android.content.Context
import android.webkit.JavascriptInterface

class WebViewInterface(
  private val context: Context,
) {

  val clipboard: ClipboardManager? by lazy {
    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
  }

  @JavascriptInterface
  fun getFromClipboard(): String {
    val clip = clipboard?.primaryClip?.getItemAt(0)
    return clip?.text?.toString() ?: ""
  }
}
