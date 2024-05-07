package com.example.ybsmobilechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ybsmobilechallenge.model.ImageRepository
import com.example.ybsmobilechallenge.network.ApiHelper
import com.example.ybsmobilechallenge.network.RetrofitService.apiService
import com.example.ybsmobilechallenge.ui.screens.HomeScreen
import com.example.ybsmobilechallenge.ui.theme.YBSMobileChallengeTheme
import com.example.ybsmobilechallenge.viewmodel.ImageListViewModel
import com.example.ybsmobilechallenge.viewmodel.factory.ImageListViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiHelper = ApiHelper(apiService)
        val repository = ImageRepository(apiHelper)

        val imageListViewModel: ImageListViewModel by viewModels {
            ImageListViewModelFactory(repository)
        }

        setContent {
            YBSMobileChallengeTheme {
                MyApp(imageListViewModel)
            }
        }
    }
}

@Composable
fun MyApp(imageListViewModel: ImageListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController, imageListViewModel)
        }
//            composable("imageDetail/{imageId}") { backStackEntry ->
//                DetailScreen(navController, backStackEntry.arguments?.getString("imageId"))
//            }
//            composable("userImages/{userId}") { backStackEntry ->
//                UserScreen(navController, backStackEntry.arguments?.getString("userId"))
//            }
    }
}
