package com.example.technicaltest.movies.repository

import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import kotlinx.coroutines.flow.Flow

/**
 * Created by khalidtoak on 1/19/21.
 */

interface MovieRepository {
    fun getMovieScreenStates(): Flow<MoviesScreenState>
}