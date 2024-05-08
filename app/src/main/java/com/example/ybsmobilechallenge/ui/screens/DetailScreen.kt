package com.example.ybsmobilechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.network.ApiHelper
import com.example.ybsmobilechallenge.network.RetrofitService
import com.example.ybsmobilechallenge.ui.components.ImageDetailCard
import com.example.ybsmobilechallenge.ui.components.NavBar
import com.example.ybsmobilechallenge.util.constructUrl
import com.example.ybsmobilechallenge.viewmodel.ImageDetailsViewModel
import com.example.ybsmobilechallenge.viewmodel.factory.ImageDetailViewModelFactory

@Composable
fun DetailScreen(navController: NavController, photoId: String?, photoSecret: String?) {

    if (photoId != null && photoSecret != null) {
        val apiHelper = ApiHelper(RetrofitService.apiService)
        val repository = ImageRepository(apiHelper)

        val factory = ImageDetailViewModelFactory(repository, photoId, photoSecret)
        val imageDetailViewModel: ImageDetailsViewModel = viewModel(factory = factory)

        val photoDetails by imageDetailViewModel.photo.observeAsState()

        Scaffold(topBar = { NavBar(navController = navController) },
            content = { innerPadding ->
                if (photoDetails != null) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        ImageDetailCard(
                            photoDetails = photoDetails!!,
                            imageUrl = constructUrl(
                                photoDetails!!.server,
                                photoDetails!!.id,
                                photoDetails!!.secret
                            )
                        )
                    }
                } else {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
            }
        )
    }
}