package com.example.moviestesttask.presentation.ui.screens.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviestesttask.presentation.ui.common.MovieCardComposable
import com.example.moviestesttask.view_model.AllMoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAllMovies(
    viewModel: AllMoviesViewModel = hiltViewModel<AllMoviesViewModel>(),
) {

    val response = viewModel.movies.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
        contentAlignment = Alignment.Center,
        modifier = Modifier
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