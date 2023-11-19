package co.sentinel.cosmos.dto

import java.math.BigDecimal

data class CurrentPrice(
  val price: String,
  val upDownPrice: String,
  val lastUpDownPrice: BigDecimal,
)
