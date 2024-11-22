package com.example.moviestesttask.presentation.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.view_model.AllMoviesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFavorites(
    lazyListState: LazyListState,
    viewModel: AllMoviesViewModel = hiltViewModel<AllMoviesViewModel>(),
) {
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
            items(favorites.value.size) {
                favorites.value[it].let { movie ->

                    if (it == 0
                        ||
                        favorites.value[it - 1].releaseDate.substringBeforeLast("-")
                        != movie.releaseDate.substringBeforeLast("-")
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 16.dp, vertical = 8.dp,
                            ),
                            text = LocalDate.parse(movie.releaseDate)
                                .format(DateTimeFormatter.ofPattern("MMM yyyy")),
                        )
                    }

                    MovieCardComposable(
                        movieData = movie,
                        isFavorite = true,
                        likeButtonCallback = {},
                        dislikeButtonCallback = {
                            viewModel.removeMovieFromFavorites(movie.id)
                        },
                    )
                }
            }
        }
    }
}