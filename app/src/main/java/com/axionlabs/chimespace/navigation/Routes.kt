package com.axionlabs.chimespace.navigation

enum class Routes {
    AuthenticationScreen,
    HomeScreen,
    SettingsScreen;
    companion object{
        fun fromRoute(route: String): Routes = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            SettingsScreen.name -> SettingsScreen
            AuthenticationScreen.name -> AuthenticationScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}