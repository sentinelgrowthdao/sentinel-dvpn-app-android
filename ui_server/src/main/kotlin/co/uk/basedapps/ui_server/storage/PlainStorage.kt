package co.uk.basedapps.ui_server.storage

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlainStorage
@Inject constructor(
  private val sharedPreferences: SharedPreferences,
) : Storage {

  override fun storeKeyValue(key: String, value: String) =
    with(sharedPreferences.edit()) {
      putString(key, value)
      apply()
    }

  override fun retrieveKeyValue(key: String): String? =
    with(sharedPreferences) {
      getString(key, null)
    }

  override fun deleteKeyValue(key: String) =
    with(sharedPreferences.edit()) {
      remove(key)
      apply()
    }
}
