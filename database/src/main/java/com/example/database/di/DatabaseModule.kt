package com.example.database.di

import android.content.Context
import com.example.database.database.MovieLocalDatabase
import com.example.database.domain.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by khalidtoak on 1/18/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): MovieLocalDatabase {
        return MovieLocalDatabase.getInstance(context)!!
    }

    @Singleton
    @Provides
    fun providesMovieDao(database: MovieLocalDatabase): MovieDao {
        return database.movieDao()
    }
}