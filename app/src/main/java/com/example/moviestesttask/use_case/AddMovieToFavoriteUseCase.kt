package com.example.moviestesttask.use_case

import com.example.moviestesttask.core.SuspendUseCase
import com.example.moviestesttask.data.LocalDataRepository
import com.example.moviestesttask.model.MovieData
import javax.inject.Inject

class AddMovieToFavoriteUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
): SuspendUseCase<Unit, MovieData>() {

    override suspend fun run(params: MovieData) {
        localDataRepository.addMovieToFavorites(params)
    }
}