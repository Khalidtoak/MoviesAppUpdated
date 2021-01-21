package com.example.technicaltest.movies.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.database.data.MovieEntity

/**
 * Created by khalidtoak on 1/21/21.
 */

class MoviesDiffUtilCallback(private val oldList: List<MovieEntity>, private val newList: List<MovieEntity>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value, name) = oldList[oldPosition]
        val (_, value1, name1) = newList[newPosition]

        return name == name1 && value == value1
    }


    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}