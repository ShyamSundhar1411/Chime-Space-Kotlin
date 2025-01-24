package com.axionlabs.chimespace.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ChimeSpaceAppBarComponent
import com.axionlabs.chimespace.components.homescreen.HomeContent
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            ChimeSpaceAppBarComponent(
                title = "ChimeSpace",
                showProfile = true,
                showSettings = true
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            HomeContent(homeViewModel = homeViewModel)
        }
    }
}