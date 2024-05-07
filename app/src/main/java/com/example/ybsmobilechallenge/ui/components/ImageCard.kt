package com.example.ybsmobilechallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.ybsmobilechallenge.R

/**
 * A composable that displays an image, its tags, the user information, and a user icon,
 * with click actions for viewing image details and user images.
 *
 * @param imageUrl URL of the image to display.
 * @param tags Tags associated with the image.
 * @param user User who uploaded the image.
 * @param userIconUrl URL of the user's icon.
 * @param onImageClick Callback for when the image is clicked.
 * @param onUserClick Callback for when the user information is clicked.
 */
@Composable
fun ImageCard(
    imageUrl: String,
    tags: String,
    user: String,
    userIconUrl: String,
    onImageClick: () -> Unit,
    onUserClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        shadowElevation = 2.dp,
        modifier = Modifier.padding(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onImageClick)
        ) {
            Image(
                painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            placeholder(R.drawable.default_no_image)
                        }).build()
                ),
                contentDescription = "Photo by $user",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable(onClick = onUserClick)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(userIconUrl)
                            .crossfade(true)
                            .placeholder(R.drawable.baseline_person_24)
                            .build()
                    ),
                    contentDescription = "User Icon",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(50)),
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = user,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (tags.isNotEmpty()) {
                        Text(
                            text = "Tags: $tags",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}
