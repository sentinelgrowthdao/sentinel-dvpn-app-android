package co.sentinel.dvpn.hub.model

import java.time.Instant

data class Subscription(
  val id: Long,
  val node: String,
  val deposit: Coin,
  val denom: String,
  val expirationDate: Instant,
  var isActive: Boolean,
)
