package com.example.moviestesttask.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.moviestesttask.R
import com.example.moviestesttask.presentation.ui.common.NavTopBar
import com.example.moviestesttask.presentation.ui.screens.all.ScreenAllMovies
import com.example.moviestesttask.presentation.ui.screens.favorites.ScreenFavorites

@Composable
fun TabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val allTabListState = LazyListState()
    val favoritesTabListState = LazyListState()

    val tabs = listOf(stringResource(R.string.all), stringResource(R.string.favorites))
    Scaffold(
        topBar = {
            NavTopBar()
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TabRow(selectedTabIndex = tabIndex) {
                        tabs.forEachIndexed { index, title ->
                            Tab(text = { Text(title) },
                                selected = tabIndex == index,
                                onClick = { tabIndex = index }
                            )
                        }
                    }
                    when (tabIndex) {
                        0 -> ScreenAllMovies(allTabListState)
                        1 -> ScreenFavorites(favoritesTabListState)
                    }
                }
            }
        })
}