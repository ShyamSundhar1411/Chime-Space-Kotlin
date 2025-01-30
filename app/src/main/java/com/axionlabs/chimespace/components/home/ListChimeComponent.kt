package com.axionlabs.chimespace.components.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.axionlabs.chimespace.models.Chime
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Composable
fun ListChimeComponent(modifier: Modifier = Modifier, chimes: List<Chime>, viewModel: HomeViewModel = hiltViewModel()){
    LazyColumn {
        items(chimes.size){
            ChimeCardComponent(
                chime = chimes[it],
                homeViewModel = viewModel
            )
        }
    }
}