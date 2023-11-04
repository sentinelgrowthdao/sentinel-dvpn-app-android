package co.uk.basedapps.vpn.common.provider

interface AppDetailsProvider {
  fun getAppVersion(): String
  fun getSharedPrefsName(): String
  fun getBaseUrl(): String
}
