package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.request.LoginRequest
import com.axionlabs.chimespace.models.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("auth/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}