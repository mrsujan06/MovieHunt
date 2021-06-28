package com.movie.moviehunt.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CacheMovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}