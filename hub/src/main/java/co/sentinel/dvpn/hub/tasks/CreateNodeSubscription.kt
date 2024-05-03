package co.sentinel.dvpn.hub.tasks

import com.google.protobuf.Any

object CreateNodeSubscription {
  fun execute(
    walletAddress: String,
    nodeAddress: String,
    denom: String,
    gigabytes: Long,
    hours: Long,
  ): Any = Any.newBuilder()
    .setTypeUrl("/sentinel.node.v2.MsgSubscribeRequest")
    .setValue(
      sentinel.node.v2.Msg.MsgSubscribeRequest.newBuilder()
        .setFrom(walletAddress)
        .setNodeAddress(nodeAddress)
        .setDenom(denom)
        .setGigabytes(gigabytes)
        .setHours(hours)
        .build()
        .toByteString(),
    )
    .build()
}
