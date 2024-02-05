package co.uk.basedapps.vpn

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import co.uk.basedapps.ui_server.server.CoreServer
import co.uk.basedapps.ui_server.vpn.PermissionStatus
import co.uk.basedapps.ui_server.vpn.VPNConnector
import co.uk.basedapps.ui_server.vpn.getVpnPermissionRequest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var coreServer: CoreServer

  @Inject
  lateinit var vpnConnector: VPNConnector

  private val vpnPermissionRequest = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult(),
  ) { result ->
    lifecycleScope.launch {
      vpnConnector.permissionFlow.emit(
        when (result.resultCode) {
          Activity.RESULT_OK -> PermissionStatus.Allowed
          else -> PermissionStatus.Denied
        },
      )
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startUiServer()
    val webView = WebView(this)
    setupWebView(webView)
    setContentView(webView)
    subscribeToPermissionsRequest()
  }

  private fun startUiServer() {
    coreServer.init()
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setupWebView(webView: WebView) {
    webView.apply {
      with(settings) {
        javaScriptEnabled = true
        domStorageEnabled = true
      }
      // wait until web server started
      lifecycleScope.launch(Dispatchers.Main) {
        delay(1000)
        loadUrl("http://127.0.0.1:3876/")
      }
    }
  }

  private fun subscribeToPermissionsRequest() {
    lifecycleScope.launch {
      vpnConnector.permissionFlow.collect { status ->
        if (status == PermissionStatus.Requested) {
          val intent = getVpnPermissionRequest(this@MainActivity)
          if (intent != null) {
            vpnPermissionRequest.launch(intent)
          } else {
            vpnConnector.permissionFlow.emit(PermissionStatus.Allowed)
          }
        }
      }
    }
  }
}
