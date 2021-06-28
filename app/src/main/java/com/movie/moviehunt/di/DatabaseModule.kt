package com.movie.moviehunt.di

import android.content.Context
import androidx.room.Room
import com.movie.moviehunt.datasource.database.room.MovieDao
import com.movie.moviehunt.datasource.database.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDb(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        MovieDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao()

}