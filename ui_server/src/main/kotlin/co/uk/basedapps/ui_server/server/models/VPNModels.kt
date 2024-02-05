package co.uk.basedapps.ui_server.server.models

import com.google.gson.annotations.SerializedName

data class TunnelStatusResponse(
  @SerializedName("isConnected")
  val isConnected: Boolean,
)
