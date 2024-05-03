package co.sentinel.dvpn.hub.tasks

import com.google.protobuf.Any
import cosmos.base.v1beta1.CoinOuterClass

object CreateDirectTransaction {
  fun execute(
    walletAddress: String,
    recipientAddress: String,
    amount: String,
    denom: String,
  ): Any = Any.newBuilder()
    .setTypeUrl("/cosmos.bank.v1beta1.MsgSend")
    .setValue(
      cosmos.bank.v1beta1.Tx.MsgSend.newBuilder()
        .setFromAddress(walletAddress)
        .setToAddress(recipientAddress)
        .addAmount(
          CoinOuterClass.Coin.newBuilder()
            .setAmount(amount)
            .setDenom(denom)
            .build(),
        )
        .build()
        .toByteString(),
    )
    .build()
}
