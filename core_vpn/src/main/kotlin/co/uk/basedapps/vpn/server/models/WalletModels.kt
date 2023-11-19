package co.uk.basedapps.vpn.server.models

data class RestoreWalletRequest(
  val mnemonic: String,
)

data class WalletResponse(
  val address: String,
)

data class Balance(
  val balance: Long,
  val currency: String,
)
