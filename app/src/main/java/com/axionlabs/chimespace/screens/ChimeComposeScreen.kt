package com.axionlabs.chimespace.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ChimeSpaceAppBarComponent
import com.axionlabs.chimespace.components.ChimeSpaceBottomNavBarComponent
import com.axionlabs.chimespace.components.chimecompose.ChimeComposeFormComponent
import com.axionlabs.chimespace.navigation.Routes
import com.axionlabs.chimespace.viewmodel.ChimeComposeViewModel

@Composable
fun ChimeComposeScreen(
    navController: NavController,
    chimeComposeViewModel: ChimeComposeViewModel = hiltViewModel(),
) {
    val chimeData = chimeComposeViewModel.chimeData.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(chimeData.data) {
        if (chimeData.data?.statusCode == 201) {
            Toast.makeText(context, "Chime Created", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.HomeScreen.name) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            val errorMessage = chimeData.e?.localizedMessage ?: "An error occurred"
            Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            ChimeSpaceAppBarComponent(
                title = "Compose Chime",
                showProfile = false,
                showSettings = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onIconClick = {
                    navController.popBackStack()
                },
            )
        },
        bottomBar = {
            ChimeSpaceBottomNavBarComponent(navController = navController)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
        ) {
            ChimeComposeFormComponent(
                modifier = Modifier.padding(innerPadding),
                onSubmit = {
                    chimeComposeViewModel.createChime(it)
                },
            )
        }
    }
}
