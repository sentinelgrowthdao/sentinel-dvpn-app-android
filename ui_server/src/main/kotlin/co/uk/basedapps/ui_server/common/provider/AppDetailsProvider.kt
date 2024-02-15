package co.uk.basedapps.ui_server.common.provider

interface AppDetailsProvider {
  fun getAppVersion(): String
  fun getSharedPrefsName(): String
  fun getBaseUrl(): String
  fun getServerPort(): Int
  fun getAppToken(): String
}
