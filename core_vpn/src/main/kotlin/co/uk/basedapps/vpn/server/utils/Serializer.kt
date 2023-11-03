package co.uk.basedapps.vpn.server.utils

import co.uk.basedapps.vpn.server.error.Error
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class FailureReasonSerializer : JsonSerializer<Error.InnerError.FailureReason> {
  override fun serialize(
    src: Error.InnerError.FailureReason,
    typeOfSrc: Type,
    context: JsonSerializationContext,
  ): JsonElement {
    return JsonPrimitive(context.serialize(src, typeOfSrc).toString())
  }
}
