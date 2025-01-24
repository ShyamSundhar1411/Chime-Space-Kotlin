package com.axionlabs.chimespace.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.models.Chime
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController,homeViewModel: HomeViewModel = hiltViewModel()) {
    var chimes = emptyList<Chime>()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
                Text(text = "Home Screen")
                if(homeViewModel.data.value.data?.chimes?.isNotEmpty() == true){
                    chimes = homeViewModel.data.value.data!!.chimes

                }else{
                    Text(text = "No chimes found")
                    Text(text = homeViewModel.data.value.e.toString())
                }
            }

        }

    }
}