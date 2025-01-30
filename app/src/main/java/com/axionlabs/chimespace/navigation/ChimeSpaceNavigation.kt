package com.axionlabs.chimespace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axionlabs.chimespace.screens.HomeScreen
import com.axionlabs.chimespace.screens.SettingsScreen

@Composable
fun ChimeSpaceNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.name){
        composable(Routes.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(Routes.SettingsScreen.name){
            SettingsScreen(navController = navController)

        }

    }

}