package com.axionlabs.chimespace.models.request.chime

data class ChimeCreateOrUpdateRequest(
    val chimeContent: String,
    val chimeTitle: String,
    val isPrivate: Boolean,
)
