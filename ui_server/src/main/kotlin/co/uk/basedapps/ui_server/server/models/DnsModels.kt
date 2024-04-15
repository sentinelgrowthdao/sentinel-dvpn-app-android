package co.uk.basedapps.ui_server.server.models

import com.google.gson.annotations.SerializedName

data class DnsResponse(
  @SerializedName("name")
  val name: String,
  @SerializedName("addresses")
  val addresses: String,
)

data class DnsListResponse(
  @SerializedName("servers")
  val servers: List<DnsResponse>,
)

data class DnsRequest(
  @SerializedName("addresses")
  val addresses: String,
)
