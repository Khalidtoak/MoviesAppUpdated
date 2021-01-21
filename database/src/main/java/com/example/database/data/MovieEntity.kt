package com.example.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by khalidtoak on 1/21/21.
 */

@Entity(tableName = "movie")
data class MovieEntity(
    val title: String,
    val imageId: String,
    @PrimaryKey
    val id : Int,
    val overview: String,
    val releaseDate: String
)