package co.sentinel.dvpn.hub.tasks

import co.sentinel.dvpn.hub.model.NodeInfo
import co.sentinel.dvpn.hub.model.NodeInfoResponse
import com.google.gson.Gson
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import ru.gildor.coroutines.okhttp.await
import sentinel.node.v2.NodeOuterClass
import timber.log.Timber

object FetchNodeInfo {
  private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(3, TimeUnit.SECONDS)
    .dispatcher(
      Dispatcher().apply {
        maxRequestsPerHost = 1000
        maxRequests = 1000
      },
    )
    .build()

  private val gson = Gson()

  val semaphore = Semaphore(64)
  suspend fun execute(list: List<NodeOuterClass.Node>): MutableList<NodeInfo> {

    return withContext(Dispatchers.Default) {
      supervisorScope {
        val result = mutableListOf<NodeInfo>()
        val responses =
          list.map {
            async {
              semaphore.withPermit {
                fetchInfo(it.remoteUrl).let { response ->
                  response?.let {
                    val latency =
                      it.receivedResponseAtMillis - it.sentRequestAtMillis
                    val body = it.body?.string()
                    it.close()
                    latency to body
                  }
                }
              }
            }
          }
            .map {
              kotlin.runCatching { it.await() }
                .getOrNull()
            }

        responses.forEach { responsePair ->
          responsePair?.second?.let { body ->
            gson.runCatching {
              fromJson(
                body,
                NodeInfoResponse::class.java,
              )
            }
              .getOrNull()?.let { nodeInfo ->
              if (nodeInfo.success) {
                nodeInfo.result.apply { this?.latency = responsePair.first }
                  ?.let {
                    result.add(it)
                  }
              }
            }
          }
        }
        Timber.d("FetchNodeInfo responses count: ${responses.size}")
        Timber.d("FetchNodeInfo responses failed count: ${responses.filter { it == null }.size}")
        Timber.d("FetchNodeInfo result count: ${result.size}")
        result
      }
    }
  }

  private suspend fun fetchInfo(url: String): Response? {
    var response: Response? = null
    try {
      url.toHttpUrlOrNull()?.let {
        response = client.newCall(
          Request.Builder().url(
            it.newBuilder()
              .scheme("http")
              .addPathSegment("status")
              .build(),
          ).build(),
        ).await()
      }
    } catch (e: Exception) {
      Timber.e(e)
    }
    return response
  }
}
