package com.example.technicaltest.movies.dataSource

import com.example.database.data.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by khalidtoak on 1/21/21.
 */

interface DataSource {
    suspend fun getMovies(): List<MovieEntity>
    suspend fun saveMovies(movies : List<MovieEntity>)
}