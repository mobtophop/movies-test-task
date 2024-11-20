package com.example.moviestesttask.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TmdbApiService {

	@GET(DISCOVER_MOVIE)
	@Headers("Accept-Version: v1")
	suspend fun getLatestMovies(
		@Query("page") page: String?,
		@Query("include_adult") includeAdult: Boolean?,
		@Query("sort_by") sortBy: String?,
		@Query("vote_average.gte") voteAverageGte: String?,
		@Query("vote_count.gte") voteCountGte: String?,
	): Response<TmdbResponseData>

	companion object {
		private const val DISCOVER_MOVIE = "discover/movie"
	}
}
