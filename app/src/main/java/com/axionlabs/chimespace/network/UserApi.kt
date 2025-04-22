package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.response.user.UserProfileResponse
import com.axionlabs.chimespace.utils.RequiresAuth
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface UserApi {
    @RequiresAuth
    @GET("user/me/")
    suspend fun getProfile(): UserProfileResponse
}
