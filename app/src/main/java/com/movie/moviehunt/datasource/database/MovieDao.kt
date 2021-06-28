package com.movie.moviehunt.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cacheMovieEntity: CacheMovieEntity): Long

    @Query("SELECT * FROM movies")
    suspend fun get(): List<CacheMovieEntity>

}