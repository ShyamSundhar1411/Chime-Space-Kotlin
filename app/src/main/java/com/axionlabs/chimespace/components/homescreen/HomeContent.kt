package com.axionlabs.chimespace.components.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.axionlabs.chimespace.models.Chime
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Preview(showBackground = true)
@Composable
fun HomeContent(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = hiltViewModel()){
    var chimes = emptyList<Chime>()
    when{
        homeViewModel.data.value.loading == true -> CircularProgressIndicator()
        homeViewModel.data.value.data.toString().isNotEmpty() -> chimes = homeViewModel.data.value.data!!.chimes
    }
    Box(modifier = modifier.fillMaxSize().padding(16.dp), contentAlignment =  Alignment.TopStart){
        ListChimeComponent(chimes = chimes)
    }
}