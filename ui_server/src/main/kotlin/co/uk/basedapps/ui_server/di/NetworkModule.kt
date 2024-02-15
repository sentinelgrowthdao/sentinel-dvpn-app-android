package co.uk.basedapps.ui_server.di

import co.uk.basedapps.ui_server.common.provider.AppDetailsProvider
import co.uk.basedapps.ui_server.network.Api
import co.uk.basedapps.ui_server.network.ConnectApi
import co.uk.basedapps.ui_server.network.HeadersInterceptor
import co.uk.basedapps.ui_server.network.utils.getUnsafeOkHttpClientBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideLoggingInterceptor() = HttpLoggingInterceptor()
    .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

  @Provides
  @Singleton
  fun provideHeadersInterceptor(
    provider: AppDetailsProvider,
  ): HeadersInterceptor = HeadersInterceptor(provider)

  @Provides
  @Singleton
  fun provideOkHttp(
    headersInterceptor: HeadersInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(headersInterceptor)
    .addInterceptor(loggingInterceptor)
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(
    client: OkHttpClient,
    provider: AppDetailsProvider,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(provider.getBaseUrl())
    .addConverterFactory(ScalarsConverterFactory.create())
    .client(client)
    .build()

  @Provides
  @Singleton
  fun provideApi(retrofit: Retrofit): Api =
    retrofit.create(Api::class.java)

  @Provides
  @Singleton
  fun provideConnectApi(
    loggingInterceptor: HttpLoggingInterceptor,
  ): ConnectApi {
    val unsafeHttpClient = getUnsafeOkHttpClientBuilder()
      .addInterceptor(loggingInterceptor)
      .build()
    val unsafeRetrofit = Retrofit.Builder()
      .baseUrl("http://127.0.0.1")
      .addConverterFactory(GsonConverterFactory.create())
      .client(unsafeHttpClient)
      .build()
    return unsafeRetrofit.create(ConnectApi::class.java)
  }
}
