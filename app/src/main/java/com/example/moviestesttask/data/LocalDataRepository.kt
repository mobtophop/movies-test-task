package com.example.moviestesttask.data

import com.example.moviestesttask.model.MovieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataRepository @Inject constructor(private val localDataSource: LocalDataSource) {

	suspend fun addMovieToFavorites(movieData: MovieData) {
		localDataSource.addMovieToFavorites(movieData)
	}

	suspend fun removeMovieFromFavorites(id: Int) {
		localDataSource.removeMovieFromFavorites(id)
	}

	fun getFavorites(): Flow<List<MovieData>> {
		return localDataSource.getFavorites().map { entityList ->
			entityList.map { MovieData.mapToDomain(it) }
		}
	}

	fun getSavedMovies(): Flow<List<MovieData>> {
		return localDataSource.getSavedMovies().map { entityList ->
			entityList.map { MovieData.mapToDomain(it) }
		}
	}

}