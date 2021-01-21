package com.example.technicaltest.movieDetails.presentation.viewModels

import dagger.assisted.AssistedFactory

/**
 * Created by khalidtoak on 1/20/21.
 */

@AssistedFactory
interface MovieDetailViewModelAssistedFactory {
    fun create(movieId: Int): MovieDetailViewModel
}