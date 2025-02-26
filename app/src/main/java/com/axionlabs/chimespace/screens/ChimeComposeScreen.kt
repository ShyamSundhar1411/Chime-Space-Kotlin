package com.axionlabs.chimespace.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ChimeSpaceAppBarComponent
import com.axionlabs.chimespace.components.ChimeSpaceBottomNavBarComponent
import com.axionlabs.chimespace.components.chimecompose.ChimeComposeFormComponent
import com.axionlabs.chimespace.viewmodel.ChimeComposeViewModel

@Composable
fun ChimeComposeScreen(navController: NavController, chimeComposeViewModel: ChimeComposeViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            ChimeSpaceAppBarComponent(
                title = "Compose Chime",
                showProfile = false,
                showSettings = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onIconClick = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            ChimeSpaceBottomNavBarComponent(navController = navController)
        }
    ){innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
        ){
            ChimeComposeFormComponent(
                modifier = Modifier.padding(innerPadding),
                onSubmit = {
                    chimeComposeViewModel.createChime(it)
                    navController.popBackStack()
                }
            )
        }
    }
}