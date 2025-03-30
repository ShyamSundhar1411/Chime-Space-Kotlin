package com.axionlabs.chimespace.data

import com.axionlabs.chimespace.models.domain.Author
import com.axionlabs.chimespace.models.domain.Chime

val dummyAuthor: Author =
    Author(
        email = "john.c.breckinridge@altostrat.com",
        id = "6786a786b7710db02122bed1",
        userName = "@johndoe",
        penName = "John Doe",
    )
val dummyChime: Chime =
    Chime(
        id = "6786a786b7710db02122bed1",
        author = dummyAuthor,
        isPrivate = false,
        chimeTitle = "Mind Over Matter \uD83E\uDDE0",
        chimeContent = "It’s all about pushing boundaries, testing limits, and embracing the u,",
        createdAt = "2025-01-20T09:55:00.000+00:00",
    )
