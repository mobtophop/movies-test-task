package com.example.moviestesttask.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbResponseData(
	var page: Int?,
	var results: List<MovieResponseData>?,
)

@Serializable
data class MovieResponseData(
	val adult: Boolean?,
	@SerialName("backdrop_path")
	val backdropPath: String?,
	@SerialName("genre_ids")
	val genreIds: ArrayList<Int>?,
	val id: Int?,
	@SerialName("original_language")
	val originalLanguage: String?,
	@SerialName("original_title")
	val originalTitle: String?,
	val overview: String?,
	val popularity: Float?,
	@SerialName("poster_path")
	val posterPath: String?,
	@SerialName("release_date")
	val releaseDate: String?,
	val title: String?,
	val video: Boolean?,
	@SerialName("vote_average")
	val voteAverage: Float?,
	@SerialName("vote_count")
	val voteCount: Int?,
)
