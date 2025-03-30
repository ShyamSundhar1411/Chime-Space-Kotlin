package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest
import com.axionlabs.chimespace.models.response.chime.ChimeCreateOrUpdateResponse
import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import com.axionlabs.chimespace.utils.NoAuth
import com.axionlabs.chimespace.utils.RequiresAuth
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ChimesApi {
    @NoAuth
    @GET("chimes/")
    suspend fun getAllChimes(): ListChimeResponse
    @Headers("Content-Type: application/json")
    @RequiresAuth
    @POST("chimes/")
    suspend fun createChime(@Body chimeData: ChimeCreateOrUpdateRequest): ChimeCreateOrUpdateResponse

}