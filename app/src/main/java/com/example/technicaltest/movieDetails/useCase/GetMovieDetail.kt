package com.example.technicaltest.movieDetails.useCase

import com.example.network.data.MovieDetail
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import com.example.technicaltest.movieDetails.reposoitory.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/20/21.
 */
class GetMovieDetail @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    fun execute(movieId: Int): Flow<MovieDetailScreenState> {
        return movieDetailRepository.getMovieDetailScreenState(movieId)
    }
}