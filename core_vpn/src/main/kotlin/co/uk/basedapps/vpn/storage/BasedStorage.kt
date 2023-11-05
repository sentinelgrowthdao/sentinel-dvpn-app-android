package co.uk.basedapps.vpn.storage

import javax.inject.Inject

class BasedStorage
@Inject constructor(
  private val plainStorage: PlainStorage,
  private val secureStorage: SecureStorage,
) {

  fun storeKeyValue(key: String, value: String, isSecure: Boolean) {
    when {
      isSecure -> secureStorage.storeKeyValue(key, value)
      else -> plainStorage.storeKeyValue(key, value)
    }
  }

  fun retrieveKeyValue(key: String): Pair<String, Boolean>? =
    secureStorage.retrieveKeyValue(key)?.let { it to true }
      ?: plainStorage.retrieveKeyValue(key)?.let { it to false }

  fun deleteKeyValue(key: String) {
    secureStorage.deleteKeyValue(key)
    plainStorage.deleteKeyValue(key)
  }
}
