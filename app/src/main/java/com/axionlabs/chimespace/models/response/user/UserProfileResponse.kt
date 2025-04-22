package com.axionlabs.chimespace.models.response.user

import com.axionlabs.chimespace.models.domain.User

data class UserProfileResponse(
    val message: String,
    val profile: User,
    val statusCode: Int,
)
