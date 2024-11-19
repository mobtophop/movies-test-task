package com.example.moviestesttask.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviestesttask.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavAppBap(selectedTabIndex: Int, navController: NavController) {

    val screens: Array<Pair<String, String>> = arrayOf(
        Pair("All", NavRoute.AllMoviesScreen.route),
        Pair("Favorites", NavRoute.FavoritesScreen.route),
    )

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        title = {},
        actions = {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {

                TabRow(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .fillMaxSize(),
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.Transparent,
                ) {

                    screens.forEachIndexed { index, it ->
                        Tab(
                            selected = selectedTabIndex == index,
                            content = { Text(text = it.first) },
                            onClick = {
                                navController.navigate(
                                    route = it.second
                                )
                            },
                        )
                    }

                }
            }
        },
    )
}