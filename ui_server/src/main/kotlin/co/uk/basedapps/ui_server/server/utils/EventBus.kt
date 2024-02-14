package co.uk.basedapps.ui_server.server.utils

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class EventBus {

  private val eventsMutableFlow = MutableSharedFlow<EventBusEvent>()
  val eventsFlow: SharedFlow<EventBusEvent?>
    get() = eventsMutableFlow

  suspend fun emitEvent(event: EventBusEvent) {
    eventsMutableFlow.emit(event)
  }
}

interface EventBusEvent
