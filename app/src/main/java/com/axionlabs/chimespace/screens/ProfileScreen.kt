package com.axionlabs.chimespace.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.axionlabs.chimespace.R
import com.axionlabs.chimespace.components.ExceptionResponseComponent
import com.axionlabs.chimespace.components.LoaderComponent
import com.axionlabs.chimespace.components.profile.ChimeCardComponent
import com.axionlabs.chimespace.components.profile.ProfileHeaderComponent
import com.axionlabs.chimespace.components.profile.ProfileStatsComponent
import com.axionlabs.chimespace.components.profile.StatItemComponent
import com.axionlabs.chimespace.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val userData = profileViewModel.userData.collectAsState().value
    val chimeData = profileViewModel.chimeData.collectAsState().value

    val user = userData.data?.profile
    val chimes = chimeData.data?.chimes ?: emptyList()

    when {
        userData.loading == true || chimeData.loading == true -> {
            LoaderComponent()
            return
        }

        userData.e != null || chimeData.e != null -> {
            ExceptionResponseComponent(
                modifier = Modifier,
                message = "Something went wrong",
                tryAgainFunction = {
                    profileViewModel.getUserProfile()
                    profileViewModel.getChimeData()
                },
                showTryAgainButton = true,
            )
            return
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ProfileHeaderComponent(user = user)
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            ProfileStatsComponent(chimesCount = chimes.size)
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Button(onClick = { Log.d("Profile", "Edit Profile") }) {
                Text("Edit Profile")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(chimes) { chime ->
            ChimeCardComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                chime = chime
            )
        }
    }
}
