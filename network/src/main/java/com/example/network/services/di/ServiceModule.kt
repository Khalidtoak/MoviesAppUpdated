package com.example.network.services.di

import com.example.network.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by khalidtoak on 1/20/21.
 */

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Provides
    @Singleton
    fun providesHeadlineService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}