package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class PlanSubscriptionRequest(
  @SerializedName("denom")
  val denom: String,
  @SerializedName("address")
  val providerAddress: String,
)

data class NodeSubscriptionRequest(
  @SerializedName("denom")
  val denom: String,
  @SerializedName("gigabytes")
  val gigabytes: Long,
  @SerializedName("hours")
  val hours: Long,
)
