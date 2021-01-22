package com.example.technicaltest.movieDetails.presentation.viewModels

import com.example.base.mvx.BaseViewModel
import com.example.network.base.parseHttpError
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import com.example.technicaltest.movieDetails.useCase.GetMovieDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect

/**
 * Created by khalidtoak on 1/20/21.
 *
 * note that this viewmodel will use AssistedInjection cos we need to pass the movieId from the fragment
 * Hence the factories being created.
 */

class MovieDetailViewModel @AssistedInject constructor(
    private val getMovieDetail: GetMovieDetail,
    @Assisted private val movieId: Int
) : BaseViewModel<MovieDetailScreenState>(MovieDetailScreenState.Initial) {

    init {
        fetchMovieDetails()
    }

    fun fetchMovieDetails() {
        publish(MovieDetailScreenState.Loading)
        launchCoroutine(
            {
                getMovieDetail.execute(movieId).also {
                    it.collect { state ->
                        publish(state)
                    }
                }
            },
            {
                publish(MovieDetailScreenState.Error(parseHttpError()(it)))
            }
        )
    }
}