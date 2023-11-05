package co.uk.basedapps.vpn.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface Api {

  @GET("{path}")
  suspend fun getProxy(
    @Path("path") path: String,
    @HeaderMap headers: Map<String, String>,
    @QueryMap queries: Map<String, String>,
  ): String

  @POST("{path}")
  suspend fun postProxy(
    @Path("path") path: String,
    @HeaderMap headers: Map<String, String>,
    @Body body: String,
  ): String

  @DELETE("{path}")
  suspend fun deleteProxy(
    @Path("path") path: String,
    @HeaderMap headers: Map<String, String>,
    @QueryMap queries: Map<String, String>,
  ): String

  @PUT("{path}")
  suspend fun pupProxy(
    @Path("path") path: String,
    @HeaderMap headers: Map<String, String>,
    @Body body: String,
  ): String
}
