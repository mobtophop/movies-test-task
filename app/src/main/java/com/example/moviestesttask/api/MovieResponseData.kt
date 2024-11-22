package com.example.moviestesttask.api

import com.example.moviestesttask.core.ModelMapper
import com.example.moviestesttask.model.MovieData
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
	val genreIds: List<Int>?,
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
) {
	companion object: ModelMapper<MovieResponseData, MovieData> {
		override fun mapTo(model: MovieResponseData): MovieData {
			return MovieData(
				adult = model.adult ?: false,
				backdropPath = model.backdropPath ?: "",
				genreIds = model.genreIds ?: listOf(),
				id = model.id ?: -1,
				originalLanguage = model.originalLanguage ?: "",
				originalTitle = model.originalTitle ?: "",
				overview = model.overview ?: "",
				popularity = model.popularity ?: -1f,
				posterPath = model.posterPath ?: "",
				releaseDate = model.releaseDate ?: "",
				title = model.title ?: "",
				video = model.video ?: false,
				voteAverage = model.voteAverage ?: -1f,
				voteCount = model.voteCount ?: -1,
			)
		}

		override fun mapToDomain(model: MovieData): MovieResponseData {
			return MovieResponseData(
				adult = model.adult,
				backdropPath = model.backdropPath,
				genreIds = model.genreIds,
				id = model.id,
				originalLanguage = model.originalLanguage,
				originalTitle = model.originalTitle,
				overview = model.overview,
				popularity = model.popularity,
				posterPath = model.posterPath,
				releaseDate = model.releaseDate,
				title = model.title,
				video = model.video,
				voteAverage = model.voteAverage,
				voteCount = model.voteCount,
			)
		}
	}
}
