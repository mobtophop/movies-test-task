package com.example.moviestesttask.room_db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moviestesttask.room_db.entities.RoomEntityMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoMovie {
	@Insert
	suspend fun insert(movie: RoomEntityMovie)

	@Update
	suspend fun update(movie: RoomEntityMovie)

	@Delete
	suspend fun delete(movie: RoomEntityMovie)

	@Query("SELECT * FROM saved_movies")
	fun getAllMovies(): Flow<List<RoomEntityMovie>>

	@Query("DELETE FROM saved_movies WHERE isFavorite=0")
	suspend fun removeNonFavorite()

	@Query("SELECT * FROM saved_movies WHERE id=:id")
	fun getItemById(id: Int): RoomEntityMovie?
}