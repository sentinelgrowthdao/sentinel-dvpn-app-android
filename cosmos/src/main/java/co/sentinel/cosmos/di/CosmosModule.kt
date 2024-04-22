package co.sentinel.cosmos.di

import android.content.Context
import co.sentinel.cosmos.base.BaseCosmosApp
import co.sentinel.cosmos.base.BaseData
import co.sentinel.cosmos.network.ChannelBuilder
import co.sentinel.cosmos.network.station.StationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class CosmosModule {

  @Provides
  @Singleton
  fun provideStationApi(okHttpClient: OkHttpClient): StationApi {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api-utility.cosmostation.io/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    return retrofit.create(StationApi::class.java)
  }

  @Provides
  @Singleton
  fun provideBaseCosmosApp(
    @ApplicationContext context: Context,
  ): BaseCosmosApp = object : BaseCosmosApp {
    override val context: Context = context
    override val baseDao: BaseData = BaseData(context)
    override val channelBuilder = ChannelBuilder(context)
  }
}
