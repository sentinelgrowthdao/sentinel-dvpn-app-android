package co.uk.basedapps.ui_server.server.models

import com.google.gson.annotations.SerializedName

data class RestoreWalletRequest(
  @SerializedName("mnemonic")
  val mnemonic: String,
)

data class WalletResponse(
  @SerializedName("address")
  val address: String,
)

data class CredentialsRequest(
  @SerializedName("url")
  val url: String,
  @SerializedName("nodeProtocol")
  val nodeProtocol: String,
  @SerializedName("address")
  val address: String,
  @SerializedName("session")
  val session: Long,
)
