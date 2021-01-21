package com.example.technicaltest.movieDetails.reposoitory

import com.example.database.data.MovieEntity
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import kotlinx.coroutines.flow.Flow

/**
 * Created by khalidtoak on 1/20/21.
 */
interface MovieDetailRepository {
   fun getMovieDetailScreenState(movieId : Int) : Flow<MovieDetailScreenState>
}