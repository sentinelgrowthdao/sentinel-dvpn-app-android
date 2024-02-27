package co.uk.basedapps.vpn

import android.app.Application
import co.uk.basedapps.ui_server.logs.FileLogTree
import co.uk.basedapps.ui_server.vpn.VpnInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

  @Inject
  lateinit var vpnInitializer: VpnInitializer

  @Inject
  lateinit var fileLogTree: FileLogTree

  override fun onCreate() {
    super.onCreate()
    setupTimber()
    vpnInitializer.setupVPN(this)
  }

  private fun setupTimber() {
    Timber.plant(Timber.DebugTree())
    Timber.plant(fileLogTree)
  }
}
