package co.sentinel.cosmos.base

import android.content.Context

interface BaseCosmosApp {
  val context: Context

  // todo abstract out the base data.
  val baseDao: BaseData
}
