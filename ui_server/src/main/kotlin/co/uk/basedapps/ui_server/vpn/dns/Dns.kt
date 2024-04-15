package co.uk.basedapps.ui_server.vpn.dns

sealed interface Dns {

  val name: String
  val address: String

  data object Cloudflare : Dns {
    override val name: String = "Cloudflare"
    override val address = "1.1.1.1, 1.0.0.1"
  }

  data object Google : Dns {
    override val name: String = "Google"
    override val address = "8.8.8.8, 8.8.4.4"
  }

  data object Handshake : Dns {
    override val name: String = "Handshake"
    override val address = "103.196.38.38, 103.196.38.39, 103.196.38.40"
  }

  data class Custom(override val address: String) : Dns {
    override val name: String = "Custom"
  }
}
