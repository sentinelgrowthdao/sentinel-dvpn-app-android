package co.uk.basedapps.ui_server.vpn

import co.uk.basedapps.domain_v2ray.V2RayRepository
import co.uk.basedapps.domain_wireguard.core.repo.WireguardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DdsConfigurator @Inject constructor(
  private val wireguardRepository: WireguardRepository,
  private val v2RayRepository: V2RayRepository,
) {

  suspend fun getDefaultDns(): Dns {
    val currentDns = wireguardRepository.getDefaultDns()
    return Dns.values().first { it.address == currentDns }
  }

  fun getDnsList(): List<Dns> = listOf(Dns.Cloudflare, Dns.Google, Dns.Handshake)

  suspend fun setDns(dns: Dns) {
    wireguardRepository.updateDns(dns.address)
    v2RayRepository.updateDns(dns.address)
  }

  enum class Dns(
    val address: String,
  ) {
    Cloudflare("1.1.1.1, 1.0.0.1"),
    Google("8.8.8.8, 8.8.4.4"),
    Handshake("103.196.38.38, 103.196.38.39, 103.196.38.40"),
  }
}
