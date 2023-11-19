package co.sentinel.cosmos.utils

import java.nio.ByteBuffer

fun Long.toByteArray(): ByteArray {
  return ByteBuffer.allocate(8).putLong(this).array()
}
