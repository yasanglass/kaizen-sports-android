package dev.yasan.kaizen.data.source.network

import dev.yasan.kaizen.model.Sport
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface KaizenApi {

    @Headers("Accept: application/json")
    @GET("/sports")
    suspend fun getSports(): Response<List<Sport>>

}