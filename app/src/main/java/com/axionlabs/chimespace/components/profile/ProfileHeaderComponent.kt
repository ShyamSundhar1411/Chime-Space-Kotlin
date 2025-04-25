package com.axionlabs.chimespace.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.axionlabs.chimespace.R
import com.axionlabs.chimespace.models.domain.User

@Composable
fun ProfileHeaderComponent(user: User?) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.cover_image),
            contentDescription = "Cover image",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(180.dp),
            contentScale = ContentScale.Crop,
        )

        Image(
            painter =
                rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = "https://www.gravatar.com/avatar")
                        .crossfade(true)
                        .build(),
                ),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .clip(CircleShape)
                    .align(Alignment.Center)
                    .height(100.dp),
        )
    }

    Spacer(modifier = Modifier.height(30.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        user?.userName?.let {
            Text(it, style = MaterialTheme.typography.titleLarge)
        }
        user?.penName?.let {
            Text(it, style = MaterialTheme.typography.titleMedium)
        }
    }
}
