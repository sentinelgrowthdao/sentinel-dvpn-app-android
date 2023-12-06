package co.uk.basedapps.vpn.storage

interface Storage {

  fun storeKeyValue(key: String, value: String)
  fun retrieveKeyValue(key: String): String?
  fun deleteKeyValue(key: String)
}
