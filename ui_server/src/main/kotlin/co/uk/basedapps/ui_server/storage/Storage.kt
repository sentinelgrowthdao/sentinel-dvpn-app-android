package co.uk.basedapps.ui_server.storage

interface Storage {

  fun storeKeyValue(key: String, value: String)
  fun retrieveKeyValue(key: String): String?
  fun deleteKeyValue(key: String)
}
