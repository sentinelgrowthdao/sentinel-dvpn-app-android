package co.sentinel.dvpn.hub.mapper

import co.sentinel.dvpn.hub.model.BaseSubscription
import co.sentinel.dvpn.hub.model.Coin
import co.sentinel.dvpn.hub.model.Subscription
import com.google.protobuf.Any as ProtoAny
import java.time.Instant
import sentinel.subscription.v2.Subscription.BaseSubscription as GrpcBaseSubscription
import sentinel.subscription.v2.Subscription.NodeSubscription
import sentinel.subscription.v2.Subscription.PlanSubscription
import sentinel.types.v1.StatusOuterClass

object SubscriptionMapper {
  fun map(obj: ProtoAny): Subscription? =
    when {
      obj.`is`(NodeSubscription::class.java) -> {
        val subscription = obj.unpack(NodeSubscription::class.java)
        Subscription.Node(
          base = mapBaseSubscription(subscription.base),
          nodeAddress = subscription.nodeAddress,
          gigabytes = subscription.gigabytes,
          hours = subscription.hours,
          deposit = Coin(subscription.deposit.denom, subscription.deposit.amount),
        )
      }

      obj.`is`(PlanSubscription::class.java) -> {
        val subscription = obj.unpack(PlanSubscription::class.java)
        Subscription.Plan(
          base = mapBaseSubscription(subscription.base),
          planId = subscription.planId,
          denom = subscription.denom,
        )
      }

      else -> null
    }

  private fun mapBaseSubscription(base: GrpcBaseSubscription): BaseSubscription {
    return BaseSubscription(
      id = base.id,
      walletAddress = base.address,
      expirationDate = Instant.ofEpochSecond(
        base.inactiveAt.seconds,
        base.inactiveAt.nanos.toLong(),
      ),
      isActive = base.status == StatusOuterClass.Status.STATUS_ACTIVE,
    )
  }
}
