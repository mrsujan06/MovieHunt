package com.movie.moviehunt.datasource.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movie.moviehunt.datasource.database.model.CacheMovieEntity

@Database(entities = [CacheMovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}