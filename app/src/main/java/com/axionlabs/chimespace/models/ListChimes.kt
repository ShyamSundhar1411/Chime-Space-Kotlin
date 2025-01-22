package com.axionlabs.chimespace.models

data class ListChimes(
    val chimes: List<Chime>,
    val message: String,
    val statusCode: Int
)