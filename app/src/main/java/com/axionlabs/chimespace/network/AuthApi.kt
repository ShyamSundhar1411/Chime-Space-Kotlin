package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.request.auth.LoginRequest
import com.axionlabs.chimespace.models.request.auth.SignUpRequest
import com.axionlabs.chimespace.models.response.auth.LoginResponse
import com.axionlabs.chimespace.models.response.auth.SignUpResponse
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
    @Headers("Content-Type: application/json")
    @POST("auth/signup/")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>


}