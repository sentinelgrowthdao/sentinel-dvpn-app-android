package co.uk.basedapps.domain_v2ray

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import co.uk.basedapps.domain.functional.Either
import co.uk.basedapps.domain.models.VpnTunnel
import co.uk.basedapps.domain_v2ray.core.V2RayUserPreferenceStore
import co.uk.basedapps.domain_v2ray.model.V2RayTunnel
import co.uk.basedapps.domain_v2ray.model.V2RayVpnProfile
import com.v2ray.ang.AppConfig
import com.v2ray.ang.dto.EConfigType
import com.v2ray.ang.dto.ServerConfig
import com.v2ray.ang.service.V2RayServiceManager
import com.v2ray.ang.util.MessageUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import timber.log.Timber

class V2RayRepositoryImpl(
  private val context: Context,
  private val userPreferenceStore: V2RayUserPreferenceStore,
) : V2RayRepository {

  private val isRunning = MutableStateFlow(false)

  private val stateReceiver = object : BroadcastReceiver() {
    override fun onReceive(ctx: Context?, intent: Intent?) {
      val state = intent?.getIntExtra("key", 0)
      isRunning.value = when (state) {
        AppConfig.MSG_STATE_RUNNING -> {
          Timber.d("V2Ray state: RUNNING ($state)")
          true
        }

        AppConfig.MSG_STATE_START_SUCCESS -> {
          Timber.d("V2Ray state: START_SUCCESS ($state)")
          true
        }

        else -> {
          Timber.d("V2Ray state: OTHER ($state)")
          false
        }
      }
    }
  }

  init {
    startListenBroadcast()
  }

  override suspend fun startV2Ray(
    profile: V2RayVpnProfile,
    serverId: String,
    serverName: String,
  ): Either<Unit, Unit> = withContext(Dispatchers.Main) {
    val config = composeConfig(profile)
    V2RayServiceManager.setV2RayConfig(config)
    V2RayServiceManager.startV2Ray(context)
    userPreferenceStore.setServerId(serverId)
    // wait 5 seconds to connect
    repeat(50) {
      delay(100)
      if (isRunning.value) return@withContext Either.Right(Unit)
    }
    // fail otherwise
    stopV2ray()
    Either.Left(Unit)
  }

  override suspend fun stopV2ray() {
    withContext(Dispatchers.Main) {
      V2RayServiceManager.stopV2Ray(context)
    }
  }

  override fun isConnected(): Boolean {
    return isRunning.value
  }

  override suspend fun getTunnel(tunnelName: String): VpnTunnel? {
    val serverId = userPreferenceStore.serverId.firstOrNull()
    return if (isConnected()) {
      V2RayTunnel(serverId = serverId)
    } else {
      null
    }
  }

  private fun startListenBroadcast() {
    ContextCompat.registerReceiver(
      context.applicationContext,
      stateReceiver,
      IntentFilter(AppConfig.BROADCAST_ACTION_ACTIVITY),
      ContextCompat.RECEIVER_NOT_EXPORTED,
    )
    MessageUtil.sendMsg2Service(context.applicationContext, AppConfig.MSG_REGISTER_CLIENT, "")
  }

  private fun composeConfig(profile: V2RayVpnProfile): ServerConfig {
    return ServerConfig.create(EConfigType.VMESS).apply {
      remarks = "v2ray is connected"
      outboundBean?.settings?.vnext?.get(0)?.let { vnext ->
        vnext.address = profile.address
        vnext.port = profile.listenPort.toInt()
        vnext.users[0].id = profile.uid
        vnext.users[0].alterId = 0
        vnext.users[0].security = "auto"
      }
      outboundBean?.streamSettings?.populateTransportSettings(
        transport = "grpc",
        headerType = "gun",
        host = "",
        path = "",
        seed = "",
        quicSecurity = "",
        key = "",
        mode = "gun",
        serviceName = "",
      )
    }
  }
}
