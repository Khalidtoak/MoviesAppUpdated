package com.example.technicaltest.movies.di

import com.example.database.domain.MovieDao
import com.example.network.services.MoviesService
import com.example.technicaltest.movies.dataSource.MovieLocalDataSource
import com.example.technicaltest.movies.dataSource.MoviesRemoteDataSource
import com.example.technicaltest.movies.repository.MovieRepository
import com.example.technicaltest.movies.repository.MovieRepositoryImpl
import com.example.technicaltest.movies.dataSource.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by khalidtoak on 1/19/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    fun providesMovieRepository(
        @Named("LocalDataSource") local: DataSource,
        @Named("RemoteDataSource") remote: DataSource
    ) : MovieRepository {
        return MovieRepositoryImpl(local, remote)
    }

    @Provides
    @Named("LocalDataSource")
    fun providesLocalMovieSource(movieDao: MovieDao): DataSource {
        return MovieLocalDataSource(movieDao)
    }

    @Provides
    @Named("RemoteDataSource")
    fun providesMovieRemoteSource(newsService: MoviesService): DataSource {
        return MoviesRemoteDataSource(newsService)
    }
}