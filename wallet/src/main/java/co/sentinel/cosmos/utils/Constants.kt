package co.sentinel.cosmos.utils

import co.sentinel.cosmos.model.type.Coin
import co.sentinel.cosmos.model.type.Fee

const val denom = "udvpn"
const val DEFAULT_FEE_AMOUNT = 30000L
const val UDVPN_MULTIPLIER = 0.000001
const val DEFAULT_GAS = 300000
val DEFAULT_FEE =
  Fee(DEFAULT_GAS.toString(), arrayListOf(Coin(denom, DEFAULT_FEE_AMOUNT.toString())))

const val DVPN_NODE_NAME = "SolarDVPN"
