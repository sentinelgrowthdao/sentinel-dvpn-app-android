package co.uk.basedapps.domain.extension

import android.app.ActivityManager
import android.content.Context
import android.os.Process

fun Context.isMainProcess(): Boolean {
  return packageName.equals(getProcessName())
}

fun Context.getProcessName(): String? {
  val manager = getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
  manager?.runningAppProcesses?.forEach { process ->
    if (process.pid == Process.myPid()) {
      return process.processName
    }
  }
  return null
}
