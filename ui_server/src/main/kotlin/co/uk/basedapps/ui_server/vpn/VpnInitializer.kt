package co.uk.basedapps.ui_server.vpn

import android.content.Context
import co.sentinel.vpn.v2ray.control.V2RayInitializer
import co.uk.basedapps.domain_wireguard.core.init.WireguardInitializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VpnInitializer @Inject constructor(
  private val wireguardInitializer: WireguardInitializer,
) {

  fun setupVPN(appContext: Context) {
    V2RayInitializer.init(appContext)
    wireguardInitializer.init()
  }
}
