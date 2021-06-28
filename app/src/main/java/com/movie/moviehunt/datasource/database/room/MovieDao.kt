package com.movie.moviehunt.datasource.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movie.moviehunt.datasource.database.model.CacheMovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cacheMovieEntity: CacheMovieEntity): Long

    @Query("SELECT * FROM movies")
    suspend fun get(): List<CacheMovieEntity>

}