package com.example.technicaltest.movieDetails.di

import com.example.database.domain.MovieDao
import com.example.network.services.MoviesService
import com.example.technicaltest.movieDetails.dataSource.MovieDetailLocalSource
import com.example.technicaltest.movieDetails.dataSource.MovieDetailRemoteDataSource
import com.example.technicaltest.movieDetails.dataSource.MovieDetailsDataSource
import com.example.technicaltest.movieDetails.reposoitory.MovieDetailRepository
import com.example.technicaltest.movieDetails.reposoitory.MovieDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by khalidtoak on 1/21/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {
    @Provides
    fun providesMovieDetailRepository(
        @Named("MovieDetailLocal") local: MovieDetailsDataSource,
        @Named("MovieDetailRemote") remote: MovieDetailsDataSource
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(local, remote)
    }

    @Provides
    @Named("MovieDetailLocal")
    fun providesMovieDetailLocalSource(movieDao: MovieDao): MovieDetailsDataSource {
        return MovieDetailLocalSource(movieDao)
    }

    @Provides
    @Named("MovieDetailRemote")
    fun providesMovieDetailRemoteSource(moviesService: MoviesService): MovieDetailsDataSource {
        return MovieDetailRemoteDataSource(moviesService)
    }
}