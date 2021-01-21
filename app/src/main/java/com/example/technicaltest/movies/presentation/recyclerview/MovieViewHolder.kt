package com.example.technicaltest.movies.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.base.util.observer
import com.example.database.data.MovieEntity
import com.example.network.data.Movie
import com.example.technicaltest.databinding.MovieListItemBinding
import com.example.technicaltest.util.load

/**
 * Created by khalidtoak on 1/20/21.
 */

class MovieViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private var url by observer<String?>(null) {
        binding.coverImageView.load(it)
    }
    fun bind(movie: MovieEntity, onClickListener: ((album: MovieEntity) -> Unit)? = null) {
        binding.coverImageView.setOnClickListener {
            onClickListener?.invoke(movie)
        }
        url = movie.imageId
    }
}