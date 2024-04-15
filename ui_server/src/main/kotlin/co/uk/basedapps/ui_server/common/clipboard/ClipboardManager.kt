package co.uk.basedapps.ui_server.common.clipboard

import android.content.ClipboardManager as AndroidClipboardManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClipboardManager
@Inject constructor(
  @ApplicationContext applicationContext: Context,
) {

  private val clipboard: AndroidClipboardManager? by lazy {
    applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as AndroidClipboardManager?
  }

  fun getFromClipboard(): String? {
    val clip = clipboard?.primaryClip?.getItemAt(0)
    return clip?.text?.toString()
  }
}
