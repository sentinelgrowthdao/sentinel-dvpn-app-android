package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class GetRegistryResponse(
  val key: String,
  val value: String,
  @SerializedName("is_secure")
  val isSecure: Boolean,
)

data class PostRegistryRequest(
  val key: String,
  val value: String,
  @SerializedName("is_secure")
  val isSecure: Boolean,
)
