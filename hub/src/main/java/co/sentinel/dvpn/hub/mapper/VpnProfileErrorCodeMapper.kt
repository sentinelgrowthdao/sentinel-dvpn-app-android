package co.sentinel.dvpn.hub.mapper

import co.sentinel.dvpn.hub.failure.*

/*object VpnProfileErrorCodeMapper {
    fun map(obj: Int): ConnectToNodeFailure {
        return when (obj) {
            REQUEST_BODY_DATA_VALIDATION_FAILED,
            REQUEST_BODY_VALIDATION_FAILED -> ConnectToNodeFailure.VpnProfileRequestFailure
            SESSION_VALIDATION_FAILED,
            SUBSCRIPTION_VALIDATION_FAILED,
            NODE_VALIDATION_FAILED -> ConnectToNodeFailure.VpnProfileNodeConfigurationFailure
            DUPLICATED_SESSION_REQUEST,
            QUERY_SESSION_FAILED,
            REMOVE_PEER_FAILED -> ConnectToNodeFailure.VpnProfileSessionFailure
            QUERY_QUOTA_FAILED -> ConnectToNodeFailure.VpnProfileNoQuotaFailure
            QUOTA_EXCEEDED -> ConnectToNodeFailure.VpnProfileQuotaExceededFailure
            MAX_PEERS_LIMIT_REACHED -> ConnectToNodeFailure.VpnProfileMaximumPeerReachedFailure
            else -> ConnectToNodeFailure.VpnProfileFetchFailure
        }
    }
}*/
