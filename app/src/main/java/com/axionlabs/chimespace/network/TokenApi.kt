package com.axionlabs.chimespace.network

import com.axionlabs.chimespace.models.request.token.TokenRefreshRequest
import com.axionlabs.chimespace.models.response.token.TokenRefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface TokenApi {
    @POST("token/refresh/")
    suspend fun refreshTokens(
        @Body refreshToken: TokenRefreshRequest,
    ): TokenRefreshResponse
}
