package com.example.technicaltest.movies.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.database.data.MovieEntity
import com.example.technicaltest.databinding.MovieListItemBinding
import javax.inject.Inject

/**
 * Created by khalidtoak on 1/20/21.
 */

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MovieViewHolder>(){

    var movies = mutableListOf<MovieEntity>()

    fun setData(movies: List<MovieEntity>) {
        val diffCallback = MoviesDiffUtilCallback(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.movies.clear()
        this.movies.addAll(movies)
        diffResult.dispatchUpdatesTo(this)
    }

    var onItemClickListener: ((movie: MovieEntity) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onItemClickListener)
    }

}