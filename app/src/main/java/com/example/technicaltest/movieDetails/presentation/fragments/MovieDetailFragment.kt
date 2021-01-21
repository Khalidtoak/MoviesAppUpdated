package com.example.technicaltest.movieDetails.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.base.mvx.ext.observe
import com.example.base.util.ViewBinder
import com.example.base.util.ViewBinderImpl
import com.example.technicaltest.R
import com.example.technicaltest.databinding.FragmentMoviesDetailBinding
import com.example.technicaltest.movieDetails.presentation.screenstates.MovieDetailScreenState
import com.example.technicaltest.movieDetails.presentation.viewModels.MovieDetailViewModel
import com.example.technicaltest.movieDetails.presentation.viewModels.MovieDetailViewModelAssistedFactory
import com.example.technicaltest.movieDetails.presentation.viewModels.MovieDetailViewModelFactory
import com.example.technicaltest.util.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment(), ViewBinder<FragmentMoviesDetailBinding> by ViewBinderImpl() {

    @Inject
    lateinit var detailViewModelAssistedFactory: MovieDetailViewModelAssistedFactory

    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieDetailViewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory.provideFactory(detailViewModelAssistedFactory, args.movieId)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return initBinding(FragmentMoviesDetailBinding.inflate(layoutInflater, container, false), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(movieDetailViewModel.stateLiveData, Observer {
            when(it) {
                is MovieDetailScreenState.Initial -> {
                    // do nothing
                }
                is MovieDetailScreenState.Loading -> {
                    handleLoading()
                }
                is MovieDetailScreenState.MovieDetailFetched -> {
                    handleData(it)
                }
                is MovieDetailScreenState.Error -> {
                    handleError(it)
                }
            }
        })
    }

    private fun handleError(it: MovieDetailScreenState.Error) {
        requireBinding().errorAnimation.visibility = View.VISIBLE
        requireBinding().progressBar.visibility = View.GONE
        requireBinding().retryButton.visibility = View.VISIBLE
        requireBinding().errorMessage.text = it.message
    }

    private fun handleData(it: MovieDetailScreenState.MovieDetailFetched) {
        requireBinding().progressBar.visibility = View.GONE
        requireBinding().errorAnimation.visibility = View.GONE
        requireBinding().retryButton.visibility = View.GONE
        requireBinding().coverImageView.load(it.movieDetails.imageId)
        requireBinding().nameTextView.text = it.movieDetails.title
        requireBinding().overViewTextView.text = it.movieDetails.overview
        requireBinding().releaseDateTextView.text = getString(R.string.release_date, it.movieDetails.releaseDate)
    }

    private fun handleLoading() {
        requireBinding().progressBar.visibility = View.VISIBLE
        requireBinding().errorAnimation.visibility = View.GONE
        requireBinding().retryButton.visibility = View.GONE
    }

}