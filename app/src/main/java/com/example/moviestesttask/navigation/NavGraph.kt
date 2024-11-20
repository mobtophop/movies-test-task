package com.example.moviestesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviestesttask.presentation.ui.screens.all.ScreenAllMovies
import com.example.moviestesttask.presentation.ui.screens.favorites.ScreenFavorites
import com.example.moviestesttask.presentation.ui.screens.main.TabScreen

@Composable
fun NavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = NavRoute.MainScreen.route) {
		composable(route = NavRoute.MainScreen.route) {
			TabScreen()
		}
	}
}
