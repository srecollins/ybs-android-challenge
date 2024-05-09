package com.example.ybsmobilechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ybsmobilechallenge.ui.components.ImageCard
import com.example.ybsmobilechallenge.viewmodel.ImageListViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.ybsmobilechallenge.ui.components.NavBar
import com.example.ybsmobilechallenge.util.constructUrl
import com.example.ybsmobilechallenge.util.constructUserIconUrl
import com.example.ybsmobilechallenge.util.getConcatenatedTagsContent

@Composable
fun UserScreen(navController: NavController, viewModel: ImageListViewModel, userId: String?) {
    val photos = viewModel.photos.observeAsState(initial = emptyList())

    // initial load
    viewModel.loadPhotos(null, "", false, userId)

    Scaffold(topBar = { NavBar(navController = navController) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Column {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(
                            items = photos.value,
                            key = { photo -> photo.id }
                        ) { photo ->
                            ImageCard(
                                imageUrl = constructUrl(photo.server, photo.id, photo.secret),
                                tags = getConcatenatedTagsContent(photo.tags),
                                user = photo.owner.username,
                                userIconUrl = constructUserIconUrl(
                                    photo.owner.iconFarm.toString(),
                                    photo.owner.iconServer,
                                    photo.owner.nsid
                                ),
                                onImageClick = { navController.navigate("imageDetail/${photo.id}/${photo.secret}") },
                                onUserClick = { }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    )
}