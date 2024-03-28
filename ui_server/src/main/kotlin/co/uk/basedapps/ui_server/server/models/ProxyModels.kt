package co.uk.basedapps.ui_server.server.models

import com.google.gson.annotations.SerializedName

data class BrowserProxyRequest(
  @SerializedName("url")
  val url: String,
)
