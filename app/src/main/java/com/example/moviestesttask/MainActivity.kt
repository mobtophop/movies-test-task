package com.example.moviestesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.presentation.theme.MoviesTestTaskTheme
import com.example.moviestesttask.presentation.theme.OffWhite
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTestTaskTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = OffWhite,
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    )
                    {
                        MovieCardComposable(mockData)
                        MovieCardComposable(mockData)
                        MovieCardComposable(mockData)
                    }
                }
            }
        }
    }
}