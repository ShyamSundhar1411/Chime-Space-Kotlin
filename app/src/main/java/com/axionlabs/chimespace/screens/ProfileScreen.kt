package com.axionlabs.chimespace.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.components.ExceptionResponseComponent
import com.axionlabs.chimespace.components.LoaderComponent
import com.axionlabs.chimespace.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val userData = profileViewModel.userData.collectAsState().value
    val chimeData = profileViewModel.chimeData.collectAsState().value

    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        when {
            userData.loading == true -> LoaderComponent()
            userData.e != null ->
                ExceptionResponseComponent(modifier = Modifier, message = "Something went wrong", tryAgainFunction = {
                    profileViewModel.getUserProfile()
                }, showTryAgainButton = true)
        }
        Text(userData.data.toString())
        when {
            chimeData.loading == true -> LoaderComponent()
            chimeData.e != null ->
            ExceptionResponseComponent(modifier = Modifier, message = "Something went wrong", tryAgainFunction = {
                profileViewModel.getChimeData()
            }, showTryAgainButton = true)
        }
        Text(chimeData.data.toString())
    }
}
