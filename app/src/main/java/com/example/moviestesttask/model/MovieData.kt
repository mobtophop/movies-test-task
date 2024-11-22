package com.example.moviestesttask.model

import android.os.Parcelable
import com.example.moviestesttask.core.ModelMapper
import com.example.moviestesttask.room_db.entities.GenreIds
import com.example.moviestesttask.room_db.entities.RoomEntityMovie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
	val adult: Boolean,
	val backdropPath: String,
	val genreIds: List<Int>,
	val id: Int,
	val originalLanguage: String,
	val originalTitle: String,
	val overview: String,
	val popularity: Float,
	val posterPath: String,
	val releaseDate: String,
	val title: String,
	val video: Boolean,
	val voteAverage: Float,
	val voteCount: Int,
	var isFavorite: Boolean? = false
) : Parcelable {
	companion object : ModelMapper<MovieData, RoomEntityMovie> {
		override fun mapTo(model: MovieData): RoomEntityMovie {
			return RoomEntityMovie(
				localId = 0,
				isFavorite = model.isFavorite ?: false,
				adult = model.adult,
				backdropPath = model.backdropPath,
				genreIds = GenreIds(model.genreIds),
				id = model.id,
				originalLanguage = model.originalLanguage,
				originalTitle = model.title,
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

		override fun mapToDomain(model: RoomEntityMovie): MovieData {
			return MovieData(
				adult = model.adult,
				backdropPath = model.backdropPath,
				genreIds = model.genreIds.genreIds,
				id = model.id,
				originalLanguage = model.originalLanguage,
				originalTitle = model.title,
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