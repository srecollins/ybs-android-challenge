package com.example.ybsmobilechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ybsmobilechallenge.ui.components.ImageCard
import com.example.ybsmobilechallenge.viewmodel.ImageListViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.ybsmobilechallenge.ui.components.ExpandableFilterSection
import com.example.ybsmobilechallenge.ui.components.NavBar
import com.example.ybsmobilechallenge.util.constructUrl
import com.example.ybsmobilechallenge.util.constructUserIconUrl
import com.example.ybsmobilechallenge.util.getConcatenatedTagsContent
import com.example.ybsmobilechallenge.util.halfPadding
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, viewModel: ImageListViewModel) {
    val photos = viewModel.photos.observeAsState(initial = emptyList())

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // initial load
    viewModel.loadPhotos("Yorkshire", "", false, null)

    Scaffold(topBar = { NavBar(navController = navController) },
        bottomBar = {
            ExpandableFilterSection(
                onTextSearch = { textQuery ->
                    viewModel.loadPhotos(textQuery, "", false, null)
                },
                onTagSearch = { tagQuery, tagMode ->
                    viewModel.loadPhotos("", tagQuery, tagMode, null)
                },
                onUsernameSearch = {
                    viewModel.findByUsername(it,
                        onSuccess = { user -> navController.navigate("userImages/${user.nsid}") },
                        onFailure = { message ->
                            scope.launch {
                                snackbarHostState.showSnackbar(message)
                            }
                        })
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(halfPadding(innerPadding))
            ) {
                Column {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = innerPadding
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
                                onUserClick = { navController.navigate("userImages/${photo.owner.nsid}") }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    )
}

