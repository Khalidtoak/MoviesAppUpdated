package com.example.technicaltest.movies.presentation.viewmodels

import com.example.base.mvx.BaseViewModel
import com.example.network.base.parseHttpError
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import com.example.technicaltest.movies.usecase.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/19/21.
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel<MoviesScreenState>(MoviesScreenState.Initial) {

    init {
        fetchHeadline()
    }

    fun fetchHeadline() {
        publish(MoviesScreenState.Loading)
        launchCoroutine(
            {
                getMovies.execute().also { flow ->
                    flow.collect {
                        publish(it)
                    }
                }
            },
            {
                publish(MoviesScreenState.Error(parseHttpError()(it)))
            }
        )
    }
}