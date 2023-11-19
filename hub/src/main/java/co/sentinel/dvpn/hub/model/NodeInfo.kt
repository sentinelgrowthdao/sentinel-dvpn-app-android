package co.sentinel.dvpn.hub.model

import com.google.gson.annotations.SerializedName

data class NodeInfo(
  val address: String,
  val bandwidth: Bandwidth,
  val handshake: Handshake,
  @SerializedName("interval_set_sessions")
  val intervalSetSessions: Long,
  @SerializedName("interval_update_sessions")
  val intervalUpdateSessions: Long,
  @SerializedName("interval_update_status")
  val intervalUpdateStatus: Long,
  val location: Location,
  val moniker: String,
  @SerializedName("operator")
  val resultOperator: String,
  val peers: Int,
  val price: String,
  val provider: String,
  val type: Int,
  val version: String,
  val qos: QOS?,
  var latency: Long = 0, // millis
) {
  data class Bandwidth(
    val upload: Int,
    val download: Int,
  )

  data class QOS(
    val maxPeers: Int,
  )

  data class Handshake(
    val enable: Boolean,
    val peers: Int,
  )

  data class Location(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
  )
}
