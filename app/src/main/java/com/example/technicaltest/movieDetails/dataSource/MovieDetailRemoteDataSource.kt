package com.example.technicaltest.movieDetails.dataSource

import com.example.database.data.MovieEntity
import com.example.network.services.MoviesService
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/21/21.
 */

class MovieDetailRemoteDataSource @Inject constructor(private val api: MoviesService) : MovieDetailsDataSource {
    override suspend fun getMovieDetail(movieId: Int): MovieEntity {
        val movieDetail =  api.getMovieDetails(movieId = movieId, language = "en-US")
        return MovieEntity(movieDetail.original_title, movieDetail.poster_path, movieDetail.id, movieDetail.overview, movieDetail.release_date)
    }

}