package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class GetRegistryResponse(
  @SerializedName("key")
  val key: String,
  @SerializedName("value")
  val value: String,
  @SerializedName("is_secure")
  val isSecure: Boolean,
)

data class PostRegistryRequest(
  @SerializedName("key")
  val key: String,
  @SerializedName("value")
  val value: String,
  @SerializedName("is_secure")
  val isSecure: Boolean,
)
