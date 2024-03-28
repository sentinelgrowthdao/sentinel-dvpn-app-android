package co.uk.basedapps.ui_server.logs

import co.uk.basedapps.ui_server.server.utils.EventBusEvent

data object ShareLogsEvent : EventBusEvent

data class OpenBrowserEvent(val url: String) : EventBusEvent
