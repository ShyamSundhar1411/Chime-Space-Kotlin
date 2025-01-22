package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.models.ListChimes
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ChimesApi {
    @GET("chimes/")
    suspend fun getAllChimes(): ListChimes
}