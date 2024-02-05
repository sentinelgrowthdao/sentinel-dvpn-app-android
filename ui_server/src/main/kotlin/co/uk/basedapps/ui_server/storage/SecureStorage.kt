package co.uk.basedapps.ui_server.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import co.uk.basedapps.ui_server.common.provider.AppDetailsProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureStorage
@Inject
constructor(
  @ApplicationContext
  applicationContext: Context,
  provider: AppDetailsProvider,
) : Storage {

  // Create or retrieve the Master Key for encryption/decryption
  private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

  // Initialize/open an instance of EncryptedSharedPreferences
  private val sharedPreferences = EncryptedSharedPreferences.create(
    provider.getSharedPrefsName(),
    masterKeyAlias,
    applicationContext,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
  )

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
