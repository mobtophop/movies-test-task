package com.example.moviestesttask.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviestesttask.api.MovieResponseData
import com.example.moviestesttask.core.UseCase
import com.example.moviestesttask.data.MovieRemoteRepository
import com.example.moviestesttask.model.MovieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
	private val movieRemoteRepository: MovieRemoteRepository
) : UseCase<Flow<PagingData<MovieData>>, UseCase.None>() {

	override fun run(params: None) = movieRemoteRepository.getLatestMovies().map {
		it.map { movie -> MovieResponseData.mapTo(movie) }
	}
}