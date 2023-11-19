package co.sentinel.dvpn.hub.model

data class NodeData(
  val nodeInfo: NodeInfo,
  val subscription: Subscription?,
  var isTrusted: Boolean = false,
)
