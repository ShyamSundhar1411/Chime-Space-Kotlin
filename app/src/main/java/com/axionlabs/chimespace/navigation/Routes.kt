package com.axionlabs.chimespace.navigation

enum class Routes {
    HomeScreen;
    companion object{
        fun fromRoute(route: String): Routes = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}