package com.example.technicaltest.movieDetails.dataSource

import com.example.database.data.MovieEntity

/**
 * Created by khalidtoak on 1/21/21.
 */
interface MovieDetailsDataSource {
    suspend fun getMovieDetail(movieId: Int): MovieEntity
}