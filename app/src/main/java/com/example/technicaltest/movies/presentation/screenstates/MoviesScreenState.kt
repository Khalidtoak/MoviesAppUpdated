package com.example.technicaltest.movies.presentation.screenstates

import com.example.base.mvx.ScreenState
import com.example.database.data.MovieEntity
import com.example.network.data.Movie

/**
 * Created by khalidtoak on 1/19/21.
 */

sealed class MoviesScreenState : ScreenState {
    object Loading : MoviesScreenState()
    object Initial : MoviesScreenState()
    data class MovieFetched(val movies: List<MovieEntity>): MoviesScreenState()
    data class Error(val message: String): MoviesScreenState()
}