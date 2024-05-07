package com.example.ybsmobilechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ybsmobilechallenge.ui.components.ImageCard
import com.example.ybsmobilechallenge.viewmodel.ImageListViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.ybsmobilechallenge.model.response.constructUrl
import com.example.ybsmobilechallenge.model.response.constructUserIconUrl
import com.example.ybsmobilechallenge.ui.components.SearchBar

@Composable
fun HomeScreen(navController: NavController, viewModel: ImageListViewModel) {
    val photos = viewModel.photos.observeAsState(initial = emptyList())

    viewModel.loadPhotos("Yorkshire")

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column {
            SearchBar(onQueryChanged = { query ->
                viewModel.loadPhotos(query)
            })

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    items = photos.value,
                    key = { photo -> photo.id }  // Use unique ID for recomposition optimization
                ) { photo ->
                    ImageCard(
                        imageUrl = constructUrl(photo.server, photo.id, photo.secret),
                        tags = viewModel.getConcatenatedTagsContent(photo.tags),
                        user = photo.owner.username,
                        userIconUrl = constructUserIconUrl(
                            photo.owner.iconFarm.toString(),
                            photo.owner.iconServer,
                            photo.owner.nsid
                        ),
                        onImageClick = { navController.navigate("imageDetail/${photo.id}") },
                        onUserClick = { navController.navigate("userImages/${photo.owner.nsid}") }
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Add space between cards
                }
            }
        }
    }
}

