package com.axionlabs.chimespace.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ChimeSpaceAppBarComponent

@Composable
fun SettingsScreen(modifier: Modifier = Modifier,navController: NavController){
    Scaffold(
        topBar = {
            ChimeSpaceAppBarComponent(
                title = "Settings",
                showProfile = false,
                showSettings = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onIconClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Settings")
        }

    }
}