package co.uk.basedapps.ui_server.common.provider

interface AppDetailsProvider {
  fun getAppVersion(): String
  fun getSharedPrefsName(): String
  fun getBaseUrl(): String
  fun getServerHost(): String
  fun getServerPort(): Int
  fun getServerUrl(): String
}
