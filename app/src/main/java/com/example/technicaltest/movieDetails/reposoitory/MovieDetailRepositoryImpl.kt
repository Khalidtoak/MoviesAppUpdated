package com.example.technicaltest.movieDetails.reposoitory

import com.example.network.base.parseHttpError
import com.example.technicaltest.movieDetails.dataSource.MovieDetailsDataSource
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by khalidtoak on 1/20/21.
 */

class MovieDetailRepositoryImpl @Inject constructor(
    @Named("MovieDetailLocal") private val local: MovieDetailsDataSource,
    @Named("MovieDetailRemote") private val remote: MovieDetailsDataSource
) : MovieDetailRepository {
    override fun getMovieDetailScreenState(movieId: Int): Flow<MovieDetailScreenState> {
        return flow {
            emit(MovieDetailScreenState.Loading)
            val local = local.getMovieDetail(movieId)
            emit(MovieDetailScreenState.MovieDetailFetched(local))
            val remote = remote.getMovieDetail(movieId)
            emit(MovieDetailScreenState.MovieDetailFetched(remote))
        }.catch {
            emit(MovieDetailScreenState.Error(parseHttpError()(it)))
        }.flowOn(Dispatchers.IO)
    }

}