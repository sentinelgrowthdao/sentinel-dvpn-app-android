package co.uk.basedapps.vpn.server.models

import com.google.gson.annotations.SerializedName

data class RestoreWalletRequest(
  @SerializedName("mnemonic")
  val mnemonic: String,
)

data class WalletResponse(
  @SerializedName("address")
  val address: String,
)
