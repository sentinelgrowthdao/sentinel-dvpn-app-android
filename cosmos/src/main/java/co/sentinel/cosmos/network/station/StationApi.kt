package co.sentinel.cosmos.network.station

import co.sentinel.cosmos.dao.Price
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StationApi {

    @GET("v1/market/price")
    suspend fun getPrice(@Query("id") denoms: String): Response<ArrayList<Price>>

}