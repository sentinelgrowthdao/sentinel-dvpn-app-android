package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.dao.Account
import com.google.protobuf2.Any
import sentinel.subscription.v2.Msg

object CancelNodeSubscription {
  fun execute(account: Account, id: Long): Any = Any.newBuilder()
    .setTypeUrl("/sentinel.subscription.v2.MsgCancelRequest")
    .setValue(
      Msg.MsgCancelRequest.newBuilder()
        .setFrom(account.address)
        .setId(id)
        .build()
        .toByteString(),
    ).build()
}
