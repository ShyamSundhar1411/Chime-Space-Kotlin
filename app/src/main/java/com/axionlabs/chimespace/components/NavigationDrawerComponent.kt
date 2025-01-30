package com.axionlabs.chimespace.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.axionlabs.chimespace.data.MenuItem
import com.axionlabs.chimespace.navigation.Routes
import com.axionlabs.chimespace.screens.SettingsScreen

@Composable
fun NavigationDrawerComponent(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit,

    ) {

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
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 12.dp,bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PersonOutline,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = "John Doe",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "@johndoe",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray
                            )
                        )
                        Row(

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("14 ")
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Following")
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = buildAnnotatedString {
                                    append("20 ")
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Followers")
                                    }
                                }
                            )
                        }
                    }
                    HorizontalDivider()
                    Spacer(Modifier.height(12.dp))
                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.label) },
                            icon = {Icon(item.icon, contentDescription = item.label)},
                            selected = false,
                            onClick = { navController.navigate(item.route) },
                        )
                    }
                }
            }
        },
        drawerState = drawerState) {
        content(PaddingValues(0.dp))

    }
}