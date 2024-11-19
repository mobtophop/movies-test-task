package com.example.moviestesttask.presentation.ui.screens.all

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.presentation.ui.common.NavAppBap

val mockData = MovieData(
    adult = false,
    backdropPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
    genreIds = arrayListOf(878, 28, 12),
    id = 912649,
    originalLanguage = "en",
    originalTitle = "Venom: The Last Dance",
    overview = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
    popularity = 4301.968F,
    posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
    releaseDate = "2024-10-22",
    title = "Venom: The Last Dance",
    video = false,
    voteAverage = 6.43F,
    voteCount = 732
)

@Composable
fun ScreenAllMovies(navController: NavController) {
    Scaffold(
        topBar = {
            NavAppBap(0, navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(ScrollState(0)),
        ) {
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
            MovieCardComposable(movieData = mockData)
        }
    }
}