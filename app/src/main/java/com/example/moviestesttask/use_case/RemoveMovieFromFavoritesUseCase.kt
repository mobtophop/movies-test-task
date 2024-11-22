package com.example.moviestesttask.use_case

import com.example.moviestesttask.core.SuspendUseCase
import com.example.moviestesttask.data.LocalDataRepository
import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) : SuspendUseCase<Unit, Int>() {

    override suspend fun run(params: Int) {
        localDataRepository.removeMovieFromFavorites(params)
    }

}