package co.uk.basedapps.ui_server.network

import co.uk.basedapps.ui_server.common.provider.AppDetailsProvider
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(
  private val provider: AppDetailsProvider,
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response = chain.run {
    proceed(
      request()
        .newBuilder()
        .apply {
          addHeader("X-App-Token", provider.getAppToken())
        }
        .build(),
    )
  }
}
