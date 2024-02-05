package co.uk.basedapps.ui_server.server.models

import com.google.gson.annotations.SerializedName

data class DnsResponse(
  @SerializedName("name")
  val name: String, // "handshake"
  @SerializedName("addresses")
  val addresses: String, // "103.196.38.38, 103.196.38.39"
)

data class DnsListResponse(
  @SerializedName("servers")
  val servers: List<DnsResponse>,
)

data class DnsRequest(
  @SerializedName("server")
  val server: String, // "handshake"
)
