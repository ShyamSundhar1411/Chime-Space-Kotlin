package com.axionlabs.chimespace.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ChimeSpaceAppBarComponent
import com.axionlabs.chimespace.components.NavigationDrawerComponent
import com.axionlabs.chimespace.components.home.HomeContent
import com.axionlabs.chimespace.navigation.Routes
import com.axionlabs.chimespace.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isAuthenticated = homeViewModel.isAuthenticated.collectAsState().value
    if(!isAuthenticated){
        navController.navigate(Routes.AuthenticationScreen.name)
    }
    NavigationDrawerComponent(drawerState = drawerState,navController = navController) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                ChimeSpaceAppBarComponent(
                    title = "ChimeSpace",
                    showProfile = true,
                    showSettings = true,
                    onProfileIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                HomeContent(homeViewModel = homeViewModel)
            }
        }
    }
}