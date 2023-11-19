package co.sentinel.dvpn.hub.model

data class LastConnectedNodeData(
  val id: Long, // can either be subscriptionId for dvpn, or nodeId for stealth. Resolve based on type.
)
