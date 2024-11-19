package com.example.moviestesttask.navigation

sealed class NavRoute(val route: String) {
    data object AllMoviesScreen : NavRoute(ALL_MOVIES_ROUTE)
    data object FavoritesScreen : NavRoute(FAVORITES_ROUTE)
}

private const val ALL_MOVIES_ROUTE = "all movies screen"
private const val FAVORITES_ROUTE = "favorites screen"
