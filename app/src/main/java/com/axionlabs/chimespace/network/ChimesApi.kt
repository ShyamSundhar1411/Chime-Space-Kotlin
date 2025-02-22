package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ChimesApi {
    @GET("chimes/")
    suspend fun getAllChimes(): ListChimeResponse
    @POST("chimes/")
    suspend fun createChime()

}