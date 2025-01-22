package com.axionlabs.chimespace.models

data class Chime(
    val author: String,
    val chimeContent: String,
    val chimeTitle: String,
    val createdAt: String,
    val id: String,
    val isPrivate: Boolean
)