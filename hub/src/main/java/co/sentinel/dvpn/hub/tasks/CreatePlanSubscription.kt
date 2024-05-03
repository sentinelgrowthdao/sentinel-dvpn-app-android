package co.sentinel.dvpn.hub.tasks

import com.google.protobuf.Any

object CreatePlanSubscription {
  fun execute(
    walletAddress: String,
    planId: Long,
    denom: String,
  ): Any = Any.newBuilder()
    .setTypeUrl("/sentinel.plan.v2.MsgSubscribeRequest")
    .setValue(
      sentinel.plan.v2.Msg.MsgSubscribeRequest.newBuilder()
        .setFrom(walletAddress)
        .setId(planId)
        .setDenom(denom)
        .build()
        .toByteString(),
    )
    .build()
}
