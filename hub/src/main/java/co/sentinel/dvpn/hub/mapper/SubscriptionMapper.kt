package co.sentinel.dvpn.hub.mapper

import co.sentinel.dvpn.hub.model.Coin
import co.sentinel.dvpn.hub.model.Subscription as DomainSubscription
import com.google.protobuf.Any as ProtoAny
import java.time.Instant
import sentinel.subscription.v2.Subscription.NodeSubscription
import sentinel.types.v1.StatusOuterClass

object SubscriptionMapper {
  fun map(obj: ProtoAny): DomainSubscription? {
    val subscription = when {
      obj.`is`(NodeSubscription::class.java) -> obj.unpack(NodeSubscription::class.java)
      else -> return null
    }
    return DomainSubscription(
      id = subscription.base.id,
      node = subscription.nodeAddress,
      deposit = Coin(subscription.deposit.denom, subscription.deposit.amount),
      denom = subscription.deposit.denom,
      expirationDate = Instant.ofEpochSecond(
        subscription.base.inactiveAt.seconds,
        subscription.base.inactiveAt.nanos.toLong(),
      ),
      isActive = subscription.base.status == StatusOuterClass.Status.STATUS_ACTIVE,
    )
  }
}
