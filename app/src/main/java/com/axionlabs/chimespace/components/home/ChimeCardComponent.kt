package com.axionlabs.chimespace.components.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.axionlabs.chimespace.data.dummyChime
import com.axionlabs.chimespace.models.domain.Chime
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Preview(showBackground = true)
@Composable
fun ChimeCardComponent(modifier: Modifier =  Modifier, chime: Chime = dummyChime, homeViewModel: HomeViewModel = hiltViewModel()){
    OutlinedCard(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable { Log.d("Card Component", "Clicked") },
        elevation = CardDefaults.cardElevation(8.dp),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileComponent(modifier = modifier,author = chime.author)
            Text(text = chime.chimeTitle)
            Text(text = chime.chimeContent)
        }
    }
}