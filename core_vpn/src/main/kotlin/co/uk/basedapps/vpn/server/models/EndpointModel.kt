package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class EndpointModel(
  @SerializedName("host")
  val host: String,
  @SerializedName("port")
  val port: Int,
)
