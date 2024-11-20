package com.example.moviestesttask.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TmdbApiService {

	@GET("discover/movie")
	@Headers(
		"Accept-Version: v1",
		"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MjZhMjE1MDA1NTg3NzkzYTE5NzQ4ZGMxZDFkMzQ1YSIsIm5iZiI6MTczMTk4NDc1OS43ODA3ODQ0LCJzdWIiOiI2NzNhYWEwNDljMTZkYWZhMDZmOWUxOWYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.tnDZx_xC18FUOM0rUgI3GaBA-qRbRPquq6u-bcm_8RI"
	)
	suspend fun getLatestMovies(
		@Query("page") page: String?, // Paging 3
		@Query("include_adult") includeAdult: Boolean?,
		@Query("sort_by") sortBy: String?,
		@Query("vote_average.gte") voteAverageGte: String?,
		@Query("vote_count.gte") voteCountGte: String?,
	): Response<TmdbResponseData>
}
