package com.example.moviestesttask.room_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter


@Entity(tableName = "saved_movies")
data class RoomEntityMovie(
    @PrimaryKey(autoGenerate = true)
    var localId: Int,

    var isFavorite: Boolean,
    var adult: Boolean,
    var backdropPath: String,
    var genreIds: GenreIds,
    var id: Int,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Float,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Float,
    var voteCount: Int,
)

fun RoomEntityMovie.update(movie: RoomEntityMovie, withFavorite: Boolean = false): RoomEntityMovie {
    this.title = movie.title
    this.adult = movie.adult
    this.backdropPath = movie.backdropPath
    this.genreIds = movie.genreIds
    if (withFavorite) this.isFavorite = movie.isFavorite
    this.originalLanguage = movie.originalLanguage
    this.originalTitle = movie.originalTitle
    this.overview = movie.overview
    this.popularity = movie.popularity
    this.posterPath = movie.posterPath
    this.releaseDate = movie.releaseDate
    this.video = movie.video
    this.voteAverage = movie.voteAverage
    this.voteCount = movie.voteCount
    return this
}

data class GenreIds(var genreIds: List<Int>)

@ProvidedTypeConverter
class GenreIdsConverter {
    @TypeConverter
    fun storedStringToGenreIds(value: String): GenreIds {
        val ids: List<Int> = value.split("|").map { it.toInt() }
        return GenreIds(ids)
    }

    @TypeConverter
    fun genreIdsToStoredString(idsList: GenreIds): String {
        var value = ""

        for (id in idsList.genreIds) value += "|$id"
        value = value.removeRange(0, 1)

        return value
    }
}