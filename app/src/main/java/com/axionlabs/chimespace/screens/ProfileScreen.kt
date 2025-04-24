package com.axionlabs.chimespace.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.axionlabs.chimespace.R
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
    when {
        userData.loading == true || chimeData.loading == true-> LoaderComponent()
        userData.e != null || chimeData.e != null ->
            ExceptionResponseComponent(modifier = Modifier, message = "Something went wrong", tryAgainFunction = {
                profileViewModel.getUserProfile()
                profileViewModel.getChimeData()
            }, showTryAgainButton = true)
    }
    LazyColumn(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val user = userData.data?.profile
        val chimes = chimeData.data?.chimes ?: emptyList()
    item {
        Box{
            Image(
                painter = painterResource(id = R.drawable.cover_image)
                , contentDescription = "cover image",
                modifier = Modifier.align(Alignment.Center).fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(
                        LocalContext.current
                    ).data("https://www.gravatar.com/avatar")
                        .crossfade(true)
                        .build()
                    ,
                ),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)

            )

        }
    }


    }
}
