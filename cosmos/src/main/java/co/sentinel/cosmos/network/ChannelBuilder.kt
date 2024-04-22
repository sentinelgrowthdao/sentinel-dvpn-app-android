package co.sentinel.cosmos.network

import android.content.Context
import co.sentinel.cosmos.BuildConfig
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.Executors
import timber.log.Timber

class ChannelBuilder(private val context: Context) {

  @Volatile
  private var mainChannel: ManagedChannel? = null

  private val prefs by lazy { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

  fun getMainChannel(): ManagedChannel {
    mainChannel?.let { channel -> return channel }
    return synchronized(this) {
      mainChannel?.let { channel -> return@synchronized channel }
      val address = prefs.getString(PREFS_ADDRESS, null) ?: BuildConfig.GRPC_BASE_URL
      val port = prefs.getInt(PREFS_PORT, BuildConfig.GRPC_PORT)
      Timber.d("Cosmos channel created with the endpoint: $address:$port")
      ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .executor(Executors.newSingleThreadExecutor())
        .build()
    }
      .also { mainChannel = it }
  }

  fun setCustomEndpoint(address: String, port: Int) {
    synchronized(this) {
      if (this.mainChannel != null) {
        this.mainChannel!!.shutdownNow()
        this.mainChannel = null
      }
      prefs.edit()
        .putString(PREFS_ADDRESS, address)
        .putInt(PREFS_PORT, port)
        .apply()
      Timber.d("Cosmos endpoint has been changed: $address:$port")
    }
  }

  fun getCurrentEndpoint(): Pair<String, Int> {
    val address = prefs.getString(PREFS_ADDRESS, null) ?: BuildConfig.GRPC_BASE_URL
    val port = prefs.getInt(PREFS_PORT, BuildConfig.GRPC_PORT)
    return address to port
  }

  companion object {
    private const val PREFS_NAME = "CosmosChannelPrefs"
    private const val PREFS_ADDRESS = "PrefsAddress"
    private const val PREFS_PORT = "PrefsPort"

    const val TIME_OUT = 30L
    const val TIME_OUT_BROADCAST = 60L
  }
}
