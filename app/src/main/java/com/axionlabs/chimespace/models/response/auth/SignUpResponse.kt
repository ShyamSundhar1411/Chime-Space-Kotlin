package com.axionlabs.chimespace.models.response.auth

import com.axionlabs.chimespace.models.domain.User

data class SignUpResponse(
    val accessToken: String,
    val message: String,
    val refreshToken: String,
    val statusCode: Int,
    val user: User
)