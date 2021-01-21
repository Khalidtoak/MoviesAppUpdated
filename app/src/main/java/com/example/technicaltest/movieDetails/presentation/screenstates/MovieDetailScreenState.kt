package com.example.technicaltest.movieDetails.presentation.screenstates

import com.example.base.mvx.ScreenState
import com.example.database.data.MovieEntity

/**
 * Created by khalidtoak on 1/20/21.
 */

sealed class MovieDetailScreenState : ScreenState {
    object Initial : MovieDetailScreenState()
    data class MovieDetailFetched(val movieDetails: MovieEntity) : MovieDetailScreenState()
    object Loading : MovieDetailScreenState()
    data class Error(val message: String) : MovieDetailScreenState()
}