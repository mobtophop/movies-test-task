package com.example.moviestesttask.navigation

sealed class NavRoute(val route: String) {
    data object MainScreen: NavRoute(MAIN_SCREEN)
}

private const val MAIN_SCREEN = "main screen"
