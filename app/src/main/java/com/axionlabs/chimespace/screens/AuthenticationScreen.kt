package com.axionlabs.chimespace.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.auth.LoginFormComponent
import com.axionlabs.chimespace.viewmodel.AuthViewModel

@Composable
fun AuthenticationScreen(navController: NavController, modifier : Modifier = Modifier, authViewModel: AuthViewModel = hiltViewModel()){
    val isAuthenticated = authViewModel.isAuthenticated.collectAsState().value
    Log.d("AuthenticationScreen", "isAuthenticated: $isAuthenticated")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(isAuthenticated) {
            navController.navigate("home")
        }
        LoginFormComponent(
            modifier,
            onLogin = ({ username, password ->
                authViewModel.login(username, password)
            })
        )
    }
}