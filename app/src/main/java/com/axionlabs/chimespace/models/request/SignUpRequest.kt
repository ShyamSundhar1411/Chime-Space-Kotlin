package com.axionlabs.chimespace.models.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val penName: String,
    val userName: String
)