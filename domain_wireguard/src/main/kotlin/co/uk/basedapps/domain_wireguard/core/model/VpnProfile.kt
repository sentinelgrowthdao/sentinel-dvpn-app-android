package co.uk.basedapps.domain_wireguard.core.model

data class WireguardVpnProfile(
  val address: String,
  val host: String,
  val listenPort: String,
  val peerEndpoint: String,
  val peerPubKeyBytes: ByteArray, // todo: think about it
)
