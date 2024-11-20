package com.example.moviestesttask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviestesttask.navigation.NavGraph
import com.example.moviestesttask.presentation.theme.MoviesTestTaskTheme
import com.example.moviestesttask.view_model.ViewModelAllMovies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: ViewModelAllMovies by viewModels<ViewModelAllMovies>()

        setContent {
            MoviesTestTaskTheme {
                NavGraph(navController = rememberNavController(), viewModel)
            }
        }
    }
}