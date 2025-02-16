package com.axionlabs.chimespace.models.response

data class SignUpResponse(
    val accessToken: String,
    val message: String,
    val refreshToken: String,
    val statusCode: Int,
    val user: User
)