package com.example.network.services

import com.example.network.data.MovieDetail
import com.example.network.data.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by khalidtoak on 1/19/21.
 */

interface MoviesService {

    @GET("popular")
    suspend fun getMovies(@Query("language") language: String, @Query("page") page: Int): Movies

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("language") language: String): MovieDetail
}