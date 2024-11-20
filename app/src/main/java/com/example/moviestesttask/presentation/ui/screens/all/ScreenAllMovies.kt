package com.example.moviestesttask.presentation.ui.screens.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.presentation.ui.common.NavAppBap
import com.example.moviestesttask.view_model.ViewModelAllMovies

//val mockData = MovieData(
//	adult = false,
//	backdropPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
//	genreIds = arrayListOf(878, 28, 12),
//	id = 912649,
//	originalLanguage = "en",
//	originalTitle = "Venom: The Last Dance",
//	overview = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
//	popularity = 4301.968F,
//	posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
//	releaseDate = "2024-10-22",
//	title = "Venom: The Last Dance",
//	video = false,
//	voteAverage = 6.43F,
//	voteCount = 732
//)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAllMovies(
	navController: NavController,
	viewModel: ViewModelAllMovies,
) {
	Scaffold(
		topBar = {
			NavAppBap(0, navController)
		}
	) { innerPadding ->
		val response = viewModel.movies.collectAsLazyPagingItems()
		val isRefreshing by viewModel.isRefreshing.collectAsState()

		PullToRefreshBox(
			isRefreshing = isRefreshing,
			onRefresh = { viewModel.refresh() },
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			LazyColumn(
				modifier = Modifier.fillMaxSize(),
				verticalArrangement = Arrangement.spacedBy(4.dp),
			) {
				items(response.itemCount) {
					response[it]?.let { movie ->
						MovieCardComposable(movieData = movie)
					}
				}
			}
		}
	}
}