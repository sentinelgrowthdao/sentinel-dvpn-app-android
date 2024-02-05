package co.uk.basedapps.ui_server.network.utils

import java.lang.Exception
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import okhttp3.OkHttpClient

fun getUnsafeOkHttpClientBuilder(): OkHttpClient.Builder {
  return try {
    val trustAllCerts = arrayOf<TrustManager>(
      object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) = Unit
        override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) = Unit
        override fun getAcceptedIssuers(): Array<X509Certificate?> = arrayOf()
      },
    )

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())
    val sslSocketFactory = sslContext.socketFactory
    val trustManagerFactory: TrustManagerFactory =
      TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    trustManagerFactory.init(null as KeyStore?)
    val trustManagers: Array<TrustManager> = trustManagerFactory.trustManagers
    check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
      "Unexpected default trust managers:" + trustManagers.contentToString()
    }
    val trustManager = trustManagers[0] as X509TrustManager

    OkHttpClient.Builder()
      .sslSocketFactory(sslSocketFactory, trustManager)
      .hostnameVerifier { _, _ -> true }
  } catch (e: Exception) {
    throw RuntimeException("Can't create unsafe http client: $e")
  }
}
