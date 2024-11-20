package com.example.moviestesttask.data

import com.example.moviestesttask.api.TmdbApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: TmdbApiService) {
	suspend fun getLatestMovies(
		page: String?,
		includeAdult: Boolean?,
		sortBy: String?,
		voteAverageGte: String?,
		voteCountGte: String?,
	) = apiService.getLatestMovies(
		page = page,
		includeAdult = includeAdult,
		sortBy = sortBy,
		voteAverageGte = voteAverageGte,
		voteCountGte = voteCountGte,
	)
}