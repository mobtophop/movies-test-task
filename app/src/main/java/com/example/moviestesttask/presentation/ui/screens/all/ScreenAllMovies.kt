package com.example.moviestesttask.presentation.ui.screens.all

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.view_model.AllMoviesViewModel
import com.valentinilk.shimmer.shimmer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAllMovies(
    lazyListState: LazyListState,
    viewModel: AllMoviesViewModel = hiltViewModel<AllMoviesViewModel>(),
) {

    val response = viewModel.movies.collectAsLazyPagingItems()
    val cachedMovies = viewModel.cachedMovies.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val favorites = viewModel.favorites.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            // loading state placeholder
            if (response.itemCount == 0 && cachedMovies.itemCount == 0) {
                items(12) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .aspectRatio(2f)
                            .shimmer(),
                        content = {},
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    )
                }
            }

            val dataProvider: LazyPagingItems<MovieData> =
                if (response.itemCount != 0) response else cachedMovies

            items(dataProvider.itemCount) {
                dataProvider[it]?.let { movie ->

                    if (it == 0 || dataProvider[it - 1]?.releaseDate?.substringBeforeLast("-")
                        != movie.releaseDate.substringBeforeLast("-")
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 16.dp, vertical = 8.dp,
                            ),
                            text = LocalDate.parse(movie.releaseDate)
                                .format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.US)),
                        )
                    }

                    MovieCardComposable(
                        movieData = movie,
                        isFavorite = favorites.value.find { fav -> fav.id == movie.id } != null,
                        likeButtonCallback = {
                            viewModel.addMovieToFavorites(movie)
                        },
                        dislikeButtonCallback = {
                            viewModel.removeMovieFromFavorites(movie.id)
                        },
                    )
                }
            }
        }
    }
}