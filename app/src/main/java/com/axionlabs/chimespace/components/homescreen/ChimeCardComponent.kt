package com.axionlabs.chimespace.components.homescreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.axionlabs.chimespace.models.Chime
import com.axionlabs.chimespace.viewmodel.HomeViewModel

@Preview(showBackground = true)
@Composable
fun ChimeCardComponent(modifier: Modifier =  Modifier,chime: Chime = Chime(
    id = "6786a786b7710db02122bed1",
    author = "6786a786b7710db02122bed1",
    isPrivate = false,
    chimeTitle = "Mind Over Matter \uD83E\uDDE0",
    chimeContent = "It’s all about pushing boundaries, testing limits, and embracing the u,",
    createdAt = "2025-01-20T09:55:00.000+00:00",
),homeViewModel: HomeViewModel = hiltViewModel()){
    OutlinedCard(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable { Log.d("Card Component", "Clicked") },
        elevation = CardDefaults.cardElevation(8.dp),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileComponent()
        }
    }
}