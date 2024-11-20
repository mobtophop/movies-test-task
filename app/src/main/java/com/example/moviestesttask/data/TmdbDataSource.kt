package com.example.moviestesttask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviestesttask.api.MovieResponseData
import retrofit2.HttpException
import java.io.IOException

class TmdbDataSource(
	private val tmdbApiService: RemoteDataSource,
) : PagingSource<Int, MovieResponseData>() {
	override fun getRefreshKey(state: PagingState<Int, MovieResponseData>): Int? {
		return state.anchorPosition?.let {
			state.closestPageToPosition(it)?.prevKey?.plus(1)
				?: state.closestPageToPosition(it)?.nextKey?.minus(1)
		}
	}

	companion object {
		private const val SORT_BY = "primary_release_date.desc"
		private const val VOTE_AVG_MIN = "7"
		private const val VOTE_COUNT_MIN = "100"

	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseData> {

		return try {
			val pageNumber = params.key ?: 1
			val prevKey = if (pageNumber > 0) pageNumber - 1 else null

			val result: List<MovieResponseData>? = tmdbApiService.getLatestMovies(
				page = "$pageNumber",
				includeAdult = true,
				sortBy = SORT_BY,
				voteAverageGte = VOTE_AVG_MIN,
				voteCountGte = VOTE_COUNT_MIN,
			).body()?.results

			val nextKey = if ((result ?: listOf()).isNotEmpty()) pageNumber + 1 else null
			LoadResult.Page(
				data = result ?: listOf(), prevKey = prevKey, nextKey = nextKey
			)
		} catch (e: IOException) {
			LoadResult.Error(e)
		} catch (e: HttpException) {
			LoadResult.Error(e)
		}
	}
}