package com.example.technicaltest.movies.dataSource

import com.example.database.data.MovieEntity
import com.example.database.domain.MovieDao
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/19/21.
 */

class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) : DataSource {
    override suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getMovies()
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

}