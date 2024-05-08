package com.example.ybsmobilechallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.ybsmobilechallenge.R
import com.example.ybsmobilechallenge.model.response.PhotoDetails
import com.example.ybsmobilechallenge.util.getConcatenatedTagsContent

@Composable
fun ImageDetailCard(photoDetails: PhotoDetails, imageUrl: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Image Title
        Text(
            text = photoDetails.title.content,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Image
        Image(
            painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(R.drawable.default_no_image)
                    }).build()
            ),
            contentDescription = "Photo by ${photoDetails.owner.username}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // User Real Name
        Text(
            text = "Full Name: ${photoDetails.owner.realname}",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Username
        Text(
            text = "Username: ${photoDetails.owner.username}",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Image Description
        Text(
            text = "Description: ${photoDetails.description.content}",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Date Uploaded
        Text(
            text = "Date Taken: ${photoDetails.dates.taken}",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(1f))

        // Tags
        Text(
            text = "Tags: ${getConcatenatedTagsContent(photoDetails.tags)}",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp
        )
    }
}
