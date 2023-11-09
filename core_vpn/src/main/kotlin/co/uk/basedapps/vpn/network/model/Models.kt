package co.uk.basedapps.vpn.network.model

import androidx.annotation.StringRes
import co.uk.basedapps.vpn.R
import com.google.gson.annotations.SerializedName

data class DataObj<T>(
  @SerializedName("data")
  val data: T,
)

data class Credentials(
  @SerializedName("protocol")
  val protocol: Protocol,
  @SerializedName("payload")
  val payload: String,
  @SerializedName(value = "private_key", alternate = ["uid"])
  val privateKey: String,
)

enum class Protocol(
  val strValue: String,
  @StringRes val labelRes: Int,
) {
  WIREGUARD("WIREGUARD", R.string.settings_protocol_wireguard),
  V2RAY("V2RAY", R.string.settings_protocol_v2ray),
  NONE("NONE", R.string.settings_protocol_any),
  ;

  companion object {
    fun fromString(strValue: String) =
      values().firstOrNull { it.strValue == strValue } ?: NONE
  }
}
