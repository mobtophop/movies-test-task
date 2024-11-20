package com.example.moviestesttask.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
	fun getLatestMovies() =
		Pager(
			config = PagingConfig(20, enablePlaceholders = false),
			pagingSourceFactory = { TmdbDataSource(remoteDataSource) },
			initialKey = 1,
		).flow
}
