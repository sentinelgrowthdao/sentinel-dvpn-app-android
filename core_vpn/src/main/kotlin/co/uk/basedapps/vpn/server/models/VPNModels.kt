package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class TunnelStatusResponse(
  @SerializedName("isConnected")
  val isConnected: Boolean,
)
