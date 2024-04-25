package co.uk.basedapps.ui_server.vpn.dns

import co.sentinel.vpn.v2ray.repo.V2RayRepository
import co.uk.basedapps.domain_wireguard.core.repo.WireguardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DdsConfigurator @Inject constructor(
  private val wireguardRepository: WireguardRepository,
  private val v2RayRepository: V2RayRepository,
) {

  private val dnsList = listOf(Dns.Cloudflare, Dns.Google, Dns.Handshake)

  suspend fun getCurrentDns(): Dns {
    val currentDns = wireguardRepository.getDefaultDns()
    return dnsList.firstOrNull { it.address == currentDns } ?: Dns.Custom(currentDns)
  }

  fun getDnsList(): List<Dns> = dnsList

  suspend fun setDns(address: String) {
    wireguardRepository.updateDns(address)
    v2RayRepository.updateDns(address)
  }
}
