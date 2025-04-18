package com.axionlabs.chimespace.models.domain

data class Chime(
    val author: Author,
    val chimeContent: String,
    val chimeTitle: String,
    val createdAt: String,
    val id: String,
    val isPrivate: Boolean,
)
