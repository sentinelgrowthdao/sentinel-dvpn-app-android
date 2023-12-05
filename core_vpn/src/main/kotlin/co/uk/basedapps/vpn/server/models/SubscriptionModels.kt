package co.uk.basedapps.vpn.server.models

data class PlanSubscriptionRequest(
  val denom: String,
  val address: String,
)

data class NodeSubscriptionRequest(
  val denom: String,
  val gigabytes: Long,
  val hours: Long,
)
