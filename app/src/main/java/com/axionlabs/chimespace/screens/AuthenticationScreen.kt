package com.axionlabs.chimespace.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.LoaderComponent
import com.axionlabs.chimespace.components.auth.LoginFormComponent
import com.axionlabs.chimespace.components.auth.SignUpFormComponent
import com.axionlabs.chimespace.navigation.Routes
import com.axionlabs.chimespace.viewmodel.AuthViewModel

@Composable
fun AuthenticationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    val loginData = authViewModel.loginData.collectAsState().value
    val signUpData = authViewModel.signUpData.collectAsState().value
    val data = if (showLoginForm.value) loginData else signUpData
    val isAuthenticated = authViewModel.isAuthenticated.collectAsState().value
    val isLoading = authViewModel.isLoading.collectAsState().value
    Log.d("AuthenticationScreen", "isLoading: $isLoading")
    LaunchedEffect(isAuthenticated) {
        if(isAuthenticated){
                navController.navigate(Routes.HomeScreen.name)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            data.loading == true -> LoaderComponent()
            data.e != null -> {
                LaunchedEffect(data.e) {
                    Toast.makeText(context, "Error: ${data.e!!.message}", Toast.LENGTH_SHORT).show()
                }
            }
            data.data != null -> {
                LaunchedEffect(data.data) {
                    Log.d("AuthenticationScreen", "Data: ${data.data}")
                    Toast.makeText(context, "Welcome ${data.data}!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.HomeScreen.name) {
                        popUpTo(Routes.AuthenticationScreen.name) { inclusive = true }
                    }
                }
            }
        }
        if(isLoading){
            LoaderComponent()
        }
        else{
            if(showLoginForm.value){
                LoginFormComponent(
                    modifier = modifier,
                    onLogin = { loginRequest ->
                        authViewModel.login(loginRequest)
                    },
                    isLoading = data.loading,
                    onSignUpClick = {
                        showLoginForm.value = false
                    }
                )
            }
            else{
                SignUpFormComponent(
                    modifier = modifier,
                    onSignUp = { signUpRequest ->
                        authViewModel.signUp(signUpRequest)
                    },
                    isLoading = data.loading,
                    onLoginClick = {
                        showLoginForm.value = true
                    }
                )
            }
        }


    }
}
