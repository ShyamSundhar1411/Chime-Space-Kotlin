package com.axionlabs.chimespace.models.response.chime

import com.axionlabs.chimespace.models.domain.Chime
import com.axionlabs.chimespace.models.domain.User

data class ChimeCreateOrUpdateResponse(
    val author: User,
    val chime: Chime,
    val message: String,
    val statusCode: Int,
)
