package com.axionlabs.chimespace.screens

import android.util.Log
import android.widget.Toast
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LoginFormComponent(
            modifier,
            onLogin = {username,password ->
                authViewModel.login(username,password)
                if(authViewModel.data.value.e?.message?.isNotEmpty() == true){
                    Toast.makeText(context, "Login Failed:"+authViewModel.data.value.e.toString(), Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.HomeScreen.name)
                }
            }
        )
    }
}