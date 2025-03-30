package com.axionlabs.chimespace.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.axionlabs.chimespace.data.MenuItem
import com.axionlabs.chimespace.navigation.Routes

@Composable
fun ChimeSpaceBottomNavBarComponent(navController: NavController) {
    val menuItems =
        listOf<MenuItem>(
            MenuItem(
                icon = Icons.Filled.Home,
                label = "Home",
                route = Routes.HomeScreen.name,
            ),
            MenuItem(
                icon = Icons.Filled.Search,
                label = "Search",
                route = Routes.SettingsScreen.name,
            ),
            MenuItem(
                icon = Icons.Filled.Create,
                label = "Write",
                route = Routes.ChimeComposeScreen.name,
            ),
        )

    NavigationBar(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 24.dp),
        containerColor = Color.Transparent,
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        menuItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                    )
                },
            )
        }
    }
}
