package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.dao.Account
import com.google.protobuf2.Any

object CreateNodeSubscription {
  fun execute(
    account: Account,
    address: String,
    denom: String,
    gigabytes: Long,
  ): Any = Any.newBuilder()
    .setTypeUrl("/sentinel.node.v2.MsgSubscribeRequest")
    .setValue(
      sentinel.node.v2.Msg.MsgSubscribeRequest.newBuilder()
        .setFrom(account.address)
        .setNodeAddress(address)
        .setDenom(denom)
        .setGigabytes(gigabytes)
        .build()
        .toByteString(),
    )
    .build()
}
