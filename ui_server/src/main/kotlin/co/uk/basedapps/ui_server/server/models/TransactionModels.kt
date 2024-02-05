package co.uk.basedapps.ui_server.server.models

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

data class DirectPaymentRequest(
  @SerializedName("amount")
  val amount: String,
  @SerializedName("denom")
  val denom: String,
  @SerializedName("memo")
  val memo: String?,
)

data class SessionStartRequest(
  @SerializedName("subscriptionID")
  val subscriptionId: Long,
  @SerializedName("node")
  val nodeAddress: String,
  @SerializedName("activeSession")
  val activeSession: Long?,
)
