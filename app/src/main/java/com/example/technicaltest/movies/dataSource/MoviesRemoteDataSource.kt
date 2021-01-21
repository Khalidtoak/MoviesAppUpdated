package com.example.technicaltest.movies.dataSource

import com.example.database.data.MovieEntity
import com.example.network.data.Movie
import com.example.network.services.MoviesService
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/19/21.
 */

class MoviesRemoteDataSource @Inject constructor(private val moviesService: MoviesService): DataSource {
    override suspend fun getMovies(): List<MovieEntity> {
        val movieEntities = mutableListOf<MovieEntity>()
        return moviesService.getMovies("en-US", 1).results.mapTo(movieEntities, {
            MovieEntity(title = it.original_title, overview = it.overview, imageId = it.poster_path, id = it.id, releaseDate = it.release_date)
        })
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        // TODO("Not yet implemented")
    }


}