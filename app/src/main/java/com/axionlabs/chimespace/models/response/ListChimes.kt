package com.axionlabs.chimespace.models.response

import com.axionlabs.chimespace.models.domain.Chime

data class ListChimes(
    val chimes: List<Chime>,
    val message: String,
    val statusCode: Int
)