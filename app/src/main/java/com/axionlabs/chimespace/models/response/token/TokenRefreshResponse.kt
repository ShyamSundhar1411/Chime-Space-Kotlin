package com.axionlabs.chimespace.models.response.token

data class TokenRefreshResponse(
    val accessToken: String,
    val refreshToken: String,
    val statusCode: Int,
    val message: String

)
