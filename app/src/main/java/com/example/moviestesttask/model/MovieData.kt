package com.example.moviestesttask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
	val adult: Boolean,
	val backdropPath: String,
	val genreIds: ArrayList<Int>,
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
) : Parcelable