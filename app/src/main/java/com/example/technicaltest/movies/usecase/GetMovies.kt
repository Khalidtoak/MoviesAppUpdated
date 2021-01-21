package com.example.technicaltest.movies.usecase

import com.example.network.data.Movie
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import com.example.technicaltest.movies.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/19/21.
 */

class GetMovies @Inject constructor(private val movieRepository: MovieRepository) {
    fun execute() : Flow<MoviesScreenState> {
        return movieRepository.getMovieScreenStates()
    }
}