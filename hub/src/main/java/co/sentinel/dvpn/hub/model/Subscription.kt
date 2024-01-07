package co.sentinel.dvpn.hub.model

import java.time.Instant

sealed interface Subscription {

  data class Node(
    val base: BaseSubscription,
    val nodeAddress: String,
    val gigabytes: Long,
    val hours: Long,
    val deposit: Coin,
  ) : Subscription

  data class Plan(
    val base: BaseSubscription,
    val planId: Long,
    val denom: String,
  ) : Subscription
}

data class BaseSubscription(
  val id: Long,
  val walletAddress: String,
  val expirationDate: Instant,
  val isActive: Boolean,
)

fun List<Subscription>.nodeSubscriptions(): List<Subscription.Node> =
  this.mapNotNull { if (it is Subscription.Node) it else null }

fun List<Subscription>.planSubscriptions(): List<Subscription.Plan> =
  this.mapNotNull { if (it is Subscription.Plan) it else null }
