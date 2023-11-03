package co.uk.basedapps.vpn.server.models

data class DnsResponse(
  val name: String, // "handshake"
  val addresses: String, // "103.196.38.38, 103.196.38.39"
)

data class DnsListResponse(
  val servers: List<DnsResponse>,
)

data class DnsRequest(
  val server: String, // "handshake"
)
