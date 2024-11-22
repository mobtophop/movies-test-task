package com.example.moviestesttask.use_case

import com.example.moviestesttask.core.UseCase
import com.example.moviestesttask.data.LocalDataRepository
import com.example.moviestesttask.model.MovieData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) : UseCase<Flow<List<MovieData>>, UseCase.None>() {

    override fun run(params: None) = localDataRepository.getFavorites()

}