package com.example.technicaltest.movieDetails.presentation.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.database.data.MovieEntity
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import com.example.technicaltest.movies.presentation.viewmodels.MoviesViewModel
import com.example.technicaltest.movies.usecase.GetMovies
import com.example.technicaltest.testUtils.CoroutineRule
import com.example.technicaltest.testUtils.movies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

/**
 * Created by khalidtoak on 1/22/21.
 */

@RunWith(JUnit4::class)
class MoviesViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var getMoviesMock: GetMovies
    private lateinit var cut: MoviesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = MoviesViewModel(getMoviesMock)
    }

    @Test
    fun `verify state when GetMovies returns data`() {
        coEvery { getMoviesMock.execute() } returns flowOf(MoviesScreenState.MovieFetched(movies))

        cut.fetchHeadline()

        cut.stateLiveData.value shouldBeEqualTo MoviesScreenState.MovieFetched(movies)
    }


}