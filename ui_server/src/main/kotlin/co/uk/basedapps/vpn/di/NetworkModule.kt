package co.uk.basedapps.vpn.di

import co.uk.basedapps.vpn.common.provider.AppDetailsProvider
import co.uk.basedapps.vpn.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttp(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .addInterceptor(interceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    client: OkHttpClient,
    provider: AppDetailsProvider,
  ): Retrofit =
    Retrofit.Builder()
      .baseUrl(provider.getBaseUrl())
      .addConverterFactory(ScalarsConverterFactory.create())
      .client(client)
      .build()

  @Provides
  @Singleton
  fun provideApi(retrofit: Retrofit): Api =
    retrofit.create(Api::class.java)
}
