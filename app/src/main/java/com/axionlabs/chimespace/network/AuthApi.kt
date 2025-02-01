package com.axionlabs.chimespace.network

import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AuthApi {
    @POST("/login")
    suspend fun login(email: String, password: String)
}