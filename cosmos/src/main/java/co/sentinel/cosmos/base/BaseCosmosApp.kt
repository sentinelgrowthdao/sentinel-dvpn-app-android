package co.sentinel.cosmos.base

import android.content.Context
import co.sentinel.cosmos.network.ChannelBuilder

interface BaseCosmosApp {
  val context: Context
  val baseDao: BaseData
  val channelBuilder: ChannelBuilder
}
