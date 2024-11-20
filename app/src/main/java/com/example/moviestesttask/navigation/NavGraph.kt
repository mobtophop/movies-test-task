package com.example.moviestesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviestesttask.presentation.ui.screens.all.ScreenAllMovies
import com.example.moviestesttask.presentation.ui.screens.favorites.ScreenFavorites

@Composable
fun NavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = NavRoute.AllMoviesScreen.route) {
		composable(route = NavRoute.AllMoviesScreen.route) {
			ScreenAllMovies(navController = navController)
		}
		composable(route = NavRoute.FavoritesScreen.route) {
			ScreenFavorites(navController = navController)
		}
	}
}
