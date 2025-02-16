package com.axionlabs.chimespace.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.LoaderComponent
import com.axionlabs.chimespace.components.auth.LoginFormComponent
import com.axionlabs.chimespace.navigation.Routes
import com.axionlabs.chimespace.viewmodel.AuthViewModel

@Composable
fun AuthenticationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val data = authViewModel.data.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            data.loading == true -> LoaderComponent() // Show loading indicator
            data.e != null -> {
                LaunchedEffect(data.e) {
                    Toast.makeText(context, "Error: ${data.e!!.message}", Toast.LENGTH_SHORT).show()
                }
            }
            data.data != null -> {
                LaunchedEffect(data.data) {
                    Toast.makeText(context, "Welcome ${data.data?.user?.userName}!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.HomeScreen.name) {
                        popUpTo(Routes.AuthenticationScreen.name) { inclusive = true }
                    }
                }
            }
        }

        LoginFormComponent(
            modifier = modifier,
            onLogin = { loginRequest ->
                authViewModel.login(loginRequest)
            },
            isLoading = data.loading
        )
    }
}
