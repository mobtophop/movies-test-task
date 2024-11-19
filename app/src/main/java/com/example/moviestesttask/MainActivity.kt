package com.example.moviestesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.moviestesttask.navigation.NavGraph
import com.example.moviestesttask.presentation.theme.MoviesTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTestTaskTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}