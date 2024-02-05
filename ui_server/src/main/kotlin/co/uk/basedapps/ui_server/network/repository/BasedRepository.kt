package co.uk.basedapps.ui_server.network.repository

import co.uk.basedapps.ui_server.network.Api
import co.uk.basedapps.ui_server.network.ConnectApi
import co.uk.basedapps.ui_server.network.NetResult
import co.uk.basedapps.ui_server.network.execute
import co.uk.basedapps.ui_server.network.model.ConnectResponse
import co.uk.basedapps.ui_server.network.model.StartSessionRequest
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

@Singleton
class BasedRepository
@Inject constructor(
  private val api: Api,
  private val connectApi: ConnectApi,
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

  suspend fun connect(
    url: String,
    body: StartSessionRequest,
  ): NetResult<ConnectResponse> = execute {
    connectApi.connect(url, body)
  }

  suspend fun resetConnection() {
    withContext(Dispatchers.IO) {
      client.connectionPool.evictAll()
    }
  }
}
