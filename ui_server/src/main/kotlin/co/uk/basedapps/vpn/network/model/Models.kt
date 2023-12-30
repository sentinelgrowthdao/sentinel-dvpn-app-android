package co.uk.basedapps.vpn.network.model

import androidx.annotation.StringRes
import co.uk.basedapps.vpn.R
import com.google.gson.annotations.SerializedName

data class Credentials(
  @SerializedName("protocol")
  val protocol: Protocol,
  @SerializedName("payload")
  val payload: String,
  @SerializedName(value = "private_key", alternate = ["uid"])
  val privateKey: String,
)

data class DataWrapper<T>(
  @SerializedName("data")
  val data: T,
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
      entries.firstOrNull { it.strValue.equals(strValue, true) } ?: NONE
  }
}

data class StartSessionRequest(
  @SerializedName("key")
  val key: String,
  @SerializedName("signature")
  val signature: String,
)

data class ConnectResponse(
  @SerializedName("result")
  val result: String,
)
