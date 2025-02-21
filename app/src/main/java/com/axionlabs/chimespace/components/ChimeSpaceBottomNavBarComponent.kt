package com.axionlabs.chimespace.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.axionlabs.chimespace.data.MenuItem
import com.axionlabs.chimespace.navigation.Routes

@Composable
fun ChimeSpaceBottomNavBarComponent(navController: NavController) {
    val menuItems = listOf<MenuItem>(
        MenuItem(
            icon = Icons.Outlined.PersonOutline,
            label = "Profile",
            route = Routes.HomeScreen.name
        ),
        MenuItem(
            icon = Icons.Outlined.Settings,
            label = "Settings",
            route = Routes.SettingsScreen.name
        ),
        MenuItem(
            icon = Icons.AutoMirrored.Outlined.Help,
            label = "Help and feedback",
            route = Routes.HomeScreen.name
        ),

        )

        NavigationBar {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route
            menuItems.forEach{item->
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
                            contentDescription = item.label
                        )
                    },
                )
            }
        }

    }
