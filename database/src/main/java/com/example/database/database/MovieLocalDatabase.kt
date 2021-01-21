package com.example.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.data.MovieEntity
import com.example.database.domain.MovieDao

/**
 * Created by khalidtoak on 1/21/21.
 */

@Database(
    entities = [
    MovieEntity::class
    ], version = 1, exportSchema = false
)
abstract class MovieLocalDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object {
        private var sInstance: MovieLocalDatabase? = null

        fun getInstance(context: Context): MovieLocalDatabase? {
            if (sInstance == null) {
                synchronized(MovieLocalDatabase::class.java) {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieLocalDatabase::class.java,
                        "movie_local.db"
                    )
                        .build()
                }
            }

            return sInstance
        }
    }
}