package com.example.moviestesttask.data

import android.content.Context
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.room_db.RoomDb
import com.example.moviestesttask.room_db.entities.RoomEntityMovie
import com.example.moviestesttask.room_db.entities.update
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(@ApplicationContext appContext: Context) {

    private val roomDb = RoomDb.getDatabase(appContext)

    suspend fun addMovieToFavorites(movieData: MovieData) {
        val newEntity = MovieData.mapTo(movieData.apply { this.isFavorite = true })
        val localEntity: RoomEntityMovie? = roomDb.moviesDao().getItemById(movieData.id)

        if (localEntity != null) {
            roomDb.moviesDao().update(localEntity.update(newEntity, true))
        } else {
            roomDb.moviesDao().insert(newEntity)
        }
    }

    suspend fun clearSavedNonFavorites() {
        roomDb.moviesDao().removeNonFavorite()
    }

    suspend fun saveOrUpdateMovie(movieData: MovieData) {
        val newEntity = MovieData.mapTo(movieData)

        val localEntity: RoomEntityMovie? = roomDb.moviesDao().getItemById(movieData.id)

        if (localEntity != null) {
            roomDb.moviesDao().update(localEntity.update(newEntity))
        } else {
            roomDb.moviesDao().insert(newEntity)
        }
    }

    suspend fun removeMovieFromFavorites(id: Int) {
        val localEntity: RoomEntityMovie? = roomDb.moviesDao().getItemById(id)

        localEntity?.let { existing ->
            roomDb.moviesDao().delete(existing)
        }
    }

    fun getFavorites(): Flow<List<RoomEntityMovie>> {
        return roomDb.moviesDao().getAllMovies().map {
            it.filter { entity -> entity.isFavorite }
        }
    }

    fun getSavedMovies(): Flow<List<RoomEntityMovie>> {
        return roomDb.moviesDao().getAllMovies()
    }
}