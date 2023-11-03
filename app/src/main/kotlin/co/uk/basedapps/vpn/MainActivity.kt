package co.uk.basedapps.vpn

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val vebView = WebView(this).apply {
      loadUrl("http://127.0.0.1:3876/api/dns/list")
    }
    setContentView(vebView)
  }
}
