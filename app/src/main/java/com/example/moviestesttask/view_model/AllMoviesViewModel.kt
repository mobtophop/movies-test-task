package com.example.moviestesttask.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviestesttask.core.none
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.use_case.AddMovieToFavoriteUseCase
import com.example.moviestesttask.use_case.GetFavoritesUseCase
import com.example.moviestesttask.use_case.GetSavedMoviesUseCase
import com.example.moviestesttask.use_case.MoviesUseCase
import com.example.moviestesttask.use_case.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _movies = MutableStateFlow<PagingData<MovieData>>(PagingData.empty())
    var movies = _movies.asStateFlow()
        private set

    private val _cachedMovies = MutableStateFlow<PagingData<MovieData>>(PagingData.empty())
    var cachedMovies = _cachedMovies.asStateFlow()
        private set

    private val _favorites = MutableStateFlow<List<MovieData>>(listOf())
    var favorites = _favorites.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            getSavedMoviesUseCase.run(none()).collect {
                _cachedMovies.value = PagingData.from(it.sortedBy { movie ->
                    movie.releaseDate
                }.reversed())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase.run(none()).collectLatest {
                _favorites.value = it.sortedBy { favourite ->
                    favourite.releaseDate
                }.reversed()
            }
        }

        viewModelScope.launch {
            moviesUseCase.run(none()).cachedIn(viewModelScope).collect {
                _movies.value = it
                _isRefreshing.tryEmit(false)
            }
        }
    }

    fun refresh() {
        _isRefreshing.tryEmit(true)
        viewModelScope.launch(Dispatchers.IO) {
            moviesUseCase.run(none()).cachedIn(viewModelScope).collectLatest {
                _movies.value = it
                delay(300)
                _isRefreshing.tryEmit(false)
            }
        }
    }

    fun addMovieToFavorites(movieData: MovieData) {
        viewModelScope.launch(Dispatchers.IO) {
            addMovieToFavoriteUseCase.run(movieData)
        }
    }

    fun removeMovieFromFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeMovieFromFavoritesUseCase.run(id)
        }
    }
}