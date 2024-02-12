package co.uk.basedapps.ui_server.server.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventBus {

  private val eventsMutableFlow = MutableStateFlow<EventBusEvent?>(null)
  val eventsFlow: SharedFlow<EventBusEvent?>
    get() = eventsMutableFlow.asSharedFlow()

  suspend fun emitEvent(event: EventBusEvent) {
    eventsMutableFlow.emit(event)
  }
}

interface EventBusEvent
