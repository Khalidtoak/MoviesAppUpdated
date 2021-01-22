package com.example.technicaltest.movies.usecase

import com.example.network.base.parseHttpError
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import com.example.technicaltest.movies.repository.MovieRepositoryImpl
import com.example.technicaltest.testUtils.movies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class GetMoviesTest {

    @MockK
    internal lateinit var movieRepoMock: MovieRepositoryImpl
    private lateinit var cut: GetMovies

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetMovies(movieRepoMock)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `return movies list`() {
        val movieFetched = MoviesScreenState.MovieFetched(
            movies
        )
        coEvery { movieRepoMock.getMovieScreenStates() } returns flowOf(movieFetched)
        runBlockingTest {
            val result =  cut.execute()
            result.first() shouldBeEqualTo movieFetched
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `throws an error when repo returns an exception`() {

        val exception = UnknownHostException()

        coEvery { movieRepoMock.getMovieScreenStates() } returns flow {
            emit(MoviesScreenState.Error(parseHttpError()(exception)))
        }
        runBlockingTest {
           val result =  cut.execute()
            result.first() shouldBeEqualTo MoviesScreenState.Error(parseHttpError()(exception))
        }

    }

}