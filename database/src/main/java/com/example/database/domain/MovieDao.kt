package com.example.database.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.data.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by khalidtoak on 1/21/21.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<MovieEntity>)

    @Query("select * from movie")
    suspend fun getMovies(): List<MovieEntity>

    @Query("select * from movie where id = :movieId")
    suspend fun getMovie(movieId : Int): MovieEntity

    @Query("delete from movie")
    suspend fun deleteMovies()

}