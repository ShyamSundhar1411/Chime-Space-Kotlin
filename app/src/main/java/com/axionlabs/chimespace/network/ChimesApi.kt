package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest
import com.axionlabs.chimespace.models.response.chime.ChimeCreateOrUpdateResponse
import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ChimesApi {
    @GET("chimes/")
    suspend fun getAllChimes(): ListChimeResponse

    @POST("chimes/")
    suspend fun createChime(@Header("Authorization") authToken: String, @Body chimeData: ChimeCreateOrUpdateRequest): ChimeCreateOrUpdateResponse

}