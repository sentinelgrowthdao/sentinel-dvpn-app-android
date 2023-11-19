package co.sentinel.dvpn.hub.mapper

import co.sentinel.dvpn.hub.model.Session
import sentinel.session.v2.SessionOuterClass

object SessionMapper {
  fun map(obj: SessionOuterClass.Session) = Session(
    id = obj.id,
    node = obj.nodeAddress,
  )
}
