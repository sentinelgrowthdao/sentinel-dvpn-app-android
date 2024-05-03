package co.sentinel.dvpn.hub.tasks

import co.sentinel.cosmos.dao.Account
import com.google.protobuf.Any
import sentinel.session.v2.Msg

object GenerateStopActiveSessionMessages {
  fun execute(account: Account, sessions: List<Long>) =
    sessions.map { sessionId ->
      Any.newBuilder()
        .setTypeUrl("/sentinel.session.v2.MsgEndRequest")
        .setValue(
          Msg.MsgEndRequest.newBuilder()
            .setId(sessionId)
            .setFrom(account.address)
            .build().toByteString(),
        )
        .build()
    }
}
