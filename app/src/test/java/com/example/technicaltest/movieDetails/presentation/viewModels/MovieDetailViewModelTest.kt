package com.example.technicaltest.movieDetails.presentation.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import com.example.technicaltest.movieDetails.useCase.GetMovieDetail
import com.example.technicaltest.testUtils.CoroutineRule
import com.example.technicaltest.testUtils.movies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var getMovieDetailMock: GetMovieDetail

    private val movieId: Int = 0

    lateinit var movieDetailViewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel(getMovieDetailMock, movieId)
    }

    @Test
    fun `verify movieDetails state when GetMovieDetails returns  a Movie`() {
        coEvery {
            getMovieDetailMock.execute(movieId)
        } returns flowOf(MovieDetailScreenState.MovieDetailFetched(movies[0]))

        movieDetailViewModel.fetchMovieDetails()

        movieDetailViewModel.stateLiveData.value shouldBeEqualTo MovieDetailScreenState.MovieDetailFetched(
            movies[0]
        )
    }
}