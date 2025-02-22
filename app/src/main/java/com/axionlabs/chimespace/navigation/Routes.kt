package com.axionlabs.chimespace.navigation

enum class Routes {
    AuthenticationScreen,
    HomeScreen,
    ChimeComposeScreen,
    SettingsScreen;
    companion object{
        fun fromRoute(route: String): Routes = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            SettingsScreen.name -> SettingsScreen
            ChimeComposeScreen.name -> ChimeComposeScreen
            AuthenticationScreen.name -> AuthenticationScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}