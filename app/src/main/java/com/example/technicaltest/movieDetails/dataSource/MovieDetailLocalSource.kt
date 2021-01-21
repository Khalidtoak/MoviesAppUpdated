package com.example.technicaltest.movieDetails.dataSource

import com.example.database.data.MovieEntity
import com.example.database.domain.MovieDao
import com.example.network.data.MovieDetail
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/21/21.
 */

class MovieDetailLocalSource @Inject constructor(private val movieDao: MovieDao) : MovieDetailsDataSource{
    override suspend fun getMovieDetail(movieId: Int): MovieEntity {
        return movieDao.getMovie(movieId)
    }

}