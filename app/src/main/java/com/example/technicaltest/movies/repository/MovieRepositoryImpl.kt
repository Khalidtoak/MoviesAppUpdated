package com.example.technicaltest.movies.repository

import com.example.network.base.parseHttpError
import com.example.technicaltest.movies.dataSource.DataSource
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by khalidtoak on 1/19/21.
 */

class MovieRepositoryImpl @Inject constructor(
    @Named("LocalDataSource") private val local: DataSource,
    @Named("RemoteDataSource") private val remote: DataSource
) : MovieRepository {
    override fun getMovieScreenStates(): Flow<MoviesScreenState> {
        return flow {
            emit(MoviesScreenState.Loading)
            val cachedMovies = local.getMovies()
            if (cachedMovies.isNotEmpty()) {
                emit(MoviesScreenState.MovieFetched(cachedMovies))
            } else {
                emit(MoviesScreenState.Loading)
            }
            val remoteMovies = remote.getMovies()
            local.saveMovies(remoteMovies)
            emit(MoviesScreenState.MovieFetched(remoteMovies))
        }.catch { exception ->
            emit(MoviesScreenState.Error(parseHttpError()(exception)))
        }.flowOn(Dispatchers.IO)
    }


}