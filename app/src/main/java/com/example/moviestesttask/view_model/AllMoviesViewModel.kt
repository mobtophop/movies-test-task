package com.example.moviestesttask.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.use_case.MoviesUseCase
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
	private val moviesUseCase: MoviesUseCase
) : ViewModel() {

	private val _isRefreshing = MutableStateFlow(false)
	val isRefreshing = _isRefreshing.asStateFlow()

	private val _movies = MutableStateFlow<PagingData<MovieData>>(PagingData.empty())
	var movies = _movies.asStateFlow()
		private set

	init {
		viewModelScope.launch {
			moviesUseCase.getLatestMovies().cachedIn(viewModelScope).collect {
				_movies.value = it
				_isRefreshing.tryEmit(false)
			}
		}
	}

	fun refresh() {
		_isRefreshing.tryEmit(true)
		viewModelScope.launch(Dispatchers.IO) {
			moviesUseCase.getLatestMovies().cachedIn(viewModelScope).collectLatest {
				_movies.value = it
				delay(300)
				_isRefreshing.tryEmit(false)
			}
		}
	}
}