package com.example.moviestesttask.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviestesttask.data.MovieRemoteRepository
import com.example.moviestesttask.model.MovieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
	private val movieRemoteRepository: MovieRemoteRepository
) {
	fun getLatestMovies():
			Flow<PagingData<MovieData>> {
		return movieRemoteRepository.getLatestMovies().map {
			it.map { data ->
				MovieData(
					adult = data.adult ?: false,
					backdropPath = data.backdropPath ?: "",
					genreIds = data.genreIds ?: arrayListOf(),
					id = data.id ?: -1,
					originalLanguage = data.originalLanguage ?: "",
					originalTitle = data.originalTitle ?: "",
					overview = data.overview ?: "",
					popularity = data.popularity ?: -1f,
					posterPath = data.posterPath ?: "",
					releaseDate = data.releaseDate ?: "",
					title = data.title ?: "",
					video = data.video ?: false,
					voteAverage = data.voteAverage ?: -1f,
					voteCount = data.voteCount ?: -1,
				)
			}
		}
	}
}