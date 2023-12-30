package co.uk.basedapps.vpn.network

import co.uk.basedapps.vpn.network.model.ConnectResponse
import co.uk.basedapps.vpn.network.model.StartSessionRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ConnectApi {

  @POST
  suspend fun connect(
    @Url url: String,
    @Body body: StartSessionRequest,
  ): ConnectResponse
}
