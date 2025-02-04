package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.response.LoginResponse
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AuthApi {
    @POST("login/")
    suspend fun login(username: String, password: String): LoginResponse


}