package com.example.technicaltest.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.base.mvx.ext.observe
import com.example.base.util.ViewBinder
import com.example.base.util.ViewBinderImpl
import com.example.technicaltest.R
import com.example.technicaltest.databinding.FragmentMoviesBinding
import com.example.technicaltest.movies.presentation.recyclerview.GridAutofitLayoutManager
import com.example.technicaltest.movies.presentation.recyclerview.MoviesAdapter
import com.example.technicaltest.movies.presentation.screenstates.MoviesScreenState
import com.example.technicaltest.movies.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment(), ViewBinder<FragmentMoviesBinding> by ViewBinderImpl() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val headlineViewModel by viewModels<MoviesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return initBinding(FragmentMoviesBinding.inflate(layoutInflater, container, false), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireBinding().recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )
            adapter = moviesAdapter
        }

        moviesAdapter.onItemClickListener = {
            findNavController().navigate(MoviesFragmentDirections.actionNewsFragmentToMovieDetailFragment(it.id))
        }

        requireBinding().retryButton.setOnClickListener {
            headlineViewModel.fetchHeadline()
        }

        observe(headlineViewModel.stateLiveData, Observer {
            when (it) {
                is MoviesScreenState.Initial -> {
                    // do nothing
                }
                is MoviesScreenState.Loading -> {
                    handleLoading()
                }

                is MoviesScreenState.MovieFetched -> {
                    handleData(it)

                }
                is MoviesScreenState.Error -> {
                    handleError(it)
                }
            }
        })
    }

    private fun handleError(it: MoviesScreenState.Error) {
        requireBinding().progressBar.visibility = View.GONE
        requireBinding().errorAnimation.visibility = View.VISIBLE
        requireBinding().errorText.text = it.message
    }

    private fun handleData(it: MoviesScreenState.MovieFetched) {
        requireBinding().progressBar.visibility = View.GONE
        requireBinding().errorAnimation.visibility = View.GONE
        moviesAdapter.setData(it.movies)
    }

    private fun handleLoading() {
        requireBinding().errorAnimation.visibility = View.GONE
        requireBinding().progressBar.visibility = View.VISIBLE
    }
}