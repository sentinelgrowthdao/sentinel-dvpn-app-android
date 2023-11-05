package co.uk.basedapps.vpn.network.repository

import co.uk.basedapps.vpn.network.Api
import co.uk.basedapps.vpn.network.NetResult
import co.uk.basedapps.vpn.network.execute
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

@Singleton
class BasedRepository
@Inject constructor(
  private val api: Api,
  private val client: OkHttpClient,
) {

  suspend fun getProxy(
    path: String,
    headers: Map<String, String>,
    queries: Map<String, String>,
  ): NetResult<String> = execute { api.getProxy(path, headers, queries) }

  suspend fun postProxy(
    path: String,
    headers: Map<String, String>,
    body: String,
  ): NetResult<String> = execute { api.postProxy(path, headers, body) }

  suspend fun deleteProxy(
    path: String,
    headers: Map<String, String>,
    queries: Map<String, String>,
  ): NetResult<String> = execute { api.deleteProxy(path, headers, queries) }

  suspend fun putProxy(
    path: String,
    headers: Map<String, String>,
    body: String,
  ): NetResult<String> = execute { api.pupProxy(path, headers, body) }

  suspend fun resetConnection() {
    withContext(Dispatchers.IO) {
      client.connectionPool.evictAll()
    }
  }
}
