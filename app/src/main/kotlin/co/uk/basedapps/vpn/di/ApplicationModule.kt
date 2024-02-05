package co.uk.basedapps.vpn.di

import co.uk.basedapps.ui_server.common.provider.AppDetailsProvider
import co.uk.basedapps.vpn.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

  @Provides
  @Singleton
  fun provideAppDetailsProvider(): AppDetailsProvider =
    object : AppDetailsProvider {
      override fun getAppVersion() = BuildConfig.VERSION_NAME
      override fun getSharedPrefsName() = "co.uk.basedapps.vpn"
      override fun getBaseUrl() = BuildConfig.API_URL
      override fun getServerPort(): Int = 3876
    }
}
