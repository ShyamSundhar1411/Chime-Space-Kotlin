package com.axionlabs.chimespace.models.response.chime

import com.axionlabs.chimespace.models.domain.Chime

data class ListChimeResponse(
    val chimes: List<Chime>,
    val message: String,
    val statusCode: Int
)