package com.axionlabs.chimespace.data

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)
