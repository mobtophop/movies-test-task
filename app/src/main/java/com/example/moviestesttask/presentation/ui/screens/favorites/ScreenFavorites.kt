package com.example.moviestesttask.presentation.ui.screens.favorites

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.presentation.ui.common.NavAppBap

@Composable
fun ScreenFavorites(navController: NavController) {
    Scaffold(
        topBar = {
            NavAppBap(1, navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(ScrollState(0)),
        ) {
//            MovieCardComposable(movieData = mockData)
//            MovieCardComposable(movieData = mockData)
        }
    }
}