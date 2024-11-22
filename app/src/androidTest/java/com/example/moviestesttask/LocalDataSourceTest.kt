package com.example.moviestesttask

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviestesttask.data.LocalDataSource
import com.example.moviestesttask.model.MovieData
import com.example.moviestesttask.room_db.RoomDb
import com.example.moviestesttask.room_db.dao.MoviesDao
import com.example.moviestesttask.room_db.entities.GenreIdsConverter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    private lateinit var database: RoomDb
    private lateinit var dao: MoviesDao
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        // Create an in-memory Room database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), RoomDb::class.java
        ).addTypeConverter(GenreIdsConverter()).allowMainThreadQueries().build()

        dao = database.moviesDao()
        localDataSource = LocalDataSource(ApplicationProvider.getApplicationContext())
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun addMovieToFavorites_insertsFavoriteMovie() = runTest {
        val movie = MovieData(
            id = 1,
            title = "Inception",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Inception",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = ""
        )

        // Add to favorites
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.addMovieToFavorites(movie)

            val result = dao.getItemById(1)
            assertNotNull(result)
            assertTrue(result?.isFavorite ?: false)
        }
    }

    @Test
    fun removeMovieFromFavorites_deletesMovie() = runTest {
        val movie = MovieData(
            id = 2,
            adult = true,
            title = "The Matrix",
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "The Matrix",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = true
        )

        // Insert and then remove
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.saveOrUpdateMovie(movie)
            localDataSource.removeMovieFromFavorites(2)

            val result = dao.getItemById(2)
            assertNull(result)
        }
    }

    @Test
    fun getFavorites_returnsOnlyFavoriteMovies() = runTest {
        val movie1 = MovieData(
            id = 3,
            title = "Interstellar",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Interstellar",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = true
        )
        val movie2 = MovieData(
            id = 4,
            title = "Dunkirk",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Dunkirk",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = false
        )

        CoroutineScope(Dispatchers.IO).launch {
            // Insert movies
            localDataSource.saveOrUpdateMovie(movie1)
            localDataSource.saveOrUpdateMovie(movie2)

            // Get favorites
            val favorites = localDataSource.getFavorites().first()
            assertEquals(1, favorites.size)
            assertEquals("Interstellar", favorites[0].title)
        }
    }

    @Test
    fun updateExistingFavorite_retainsFavoriteStatus() = runTest {
        val movie = MovieData(
            id = 5,
            title = "Gladiator",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Gladiator",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = true
        )
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.addMovieToFavorites(movie)

            val updatedMovie = MovieData(
                id = 5,
                title = "Gladiator - Extended",
                adult = true,
                genreIds = listOf(1, 2),
                originalLanguage = "en",
                originalTitle = "Gladiator - Extended",
                overview = "overview",
                popularity = 4.7f,
                posterPath = "",
                releaseDate = "",
                video = true,
                voteAverage = 4.7f,
                voteCount = 57,
                backdropPath = "",
                isFavorite = true
            )
            localDataSource.saveOrUpdateMovie(updatedMovie)

            val result = dao.getItemById(5)
            assertNotNull(result)
            assertTrue(result?.isFavorite ?: false)
            assertEquals("Gladiator - Extended", result?.title)
        }
    }

    @Test
    fun clearSavedNonFavorites_removesOnlyNonFavorites() = runTest {
        val favoriteMovie = MovieData(
            id = 6,
            title = "Tenet",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Tenet",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = true
        )
        val nonFavoriteMovie = MovieData(
            id = 7,
            title = "Insomnia",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Insomnia",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = false
        )
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.saveOrUpdateMovie(favoriteMovie)
            localDataSource.saveOrUpdateMovie(nonFavoriteMovie)

            localDataSource.clearSavedNonFavorites()

            val movies = dao.getAllMovies().first()
            assertEquals(1, movies.size)
            assertEquals("Tenet", movies[0].title)
        }
    }

    @Test
    fun saveDuplicateMovie_updatesExistingEntry() = runTest {
        val movie = MovieData(
            id = 8,
            title = "Memento",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Memento",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = false
        )
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.saveOrUpdateMovie(movie)

            val duplicateMovie = MovieData(
                id = 8,
                title = "Memento - Updated",
                adult = true,
                genreIds = listOf(1, 2),
                originalLanguage = "en",
                originalTitle = "Memento - Updated",
                overview = "overview",
                popularity = 4.7f,
                posterPath = "",
                releaseDate = "",
                video = true,
                voteAverage = 4.7f,
                voteCount = 57,
                backdropPath = "",
                isFavorite = true
            )
            localDataSource.saveOrUpdateMovie(duplicateMovie)

            val result = dao.getItemById(8)
            assertNotNull(result)
            assertTrue(result?.isFavorite ?: false)
            assertEquals("Memento - Updated", result?.title)
        }
    }

    @Test
    fun getFavorites_whenNoFavorites_returnsEmptyList() = runTest {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = localDataSource.getFavorites().first()
            assertTrue(movies.isEmpty())
        }
    }

    @Test
    fun getSavedMovies_returnsAllMovies() = runTest {
        val movie1 = MovieData(
            id = 9,
            title = "Batman Begins",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Batman Begins",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = false
        )
        val movie2 = MovieData(
            id = 10,
            title = "The Dark Knight",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "The Dark Knight",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = true
        )
        CoroutineScope(Dispatchers.IO).launch {

            localDataSource.saveOrUpdateMovie(movie1)
            localDataSource.saveOrUpdateMovie(movie2)

            val allMovies = localDataSource.getSavedMovies().first()
            assertEquals(2, allMovies.size)
            assertTrue(allMovies.any { it.title == "Batman Begins" })
            assertTrue(allMovies.any { it.title == "The Dark Knight" })
        }
    }

    @Test
    fun addNonFavoriteMovie_andRetrieve_itExists() = runTest {
        val movie = MovieData(
            id = 11,
            title = "Inception",
            adult = true,
            genreIds = listOf(1, 2),
            originalLanguage = "en",
            originalTitle = "Inception",
            overview = "overview",
            popularity = 4.7f,
            posterPath = "",
            releaseDate = "",
            video = true,
            voteAverage = 4.7f,
            voteCount = 57,
            backdropPath = "",
            isFavorite = false
        )
        CoroutineScope(Dispatchers.IO).launch {

            localDataSource.saveOrUpdateMovie(movie)

            val result = dao.getItemById(11)
            assertNotNull(result)
            assertFalse(result?.isFavorite ?: false)
        }
    }
}