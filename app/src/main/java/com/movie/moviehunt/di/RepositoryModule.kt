package com.movie.moviehunt.di

import com.movie.moviehunt.datasource.database.mapper.CacheMapper
import com.movie.moviehunt.datasource.database.room.MovieDao
import com.movie.moviehunt.datasource.remote.api.MovieService
import com.movie.moviehunt.datasource.remote.mapper.NetworkMapper
import com.movie.moviehunt.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(
        movieDao: MovieDao,
        movieService: MovieService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository = MainRepository(movieDao, movieService, cacheMapper, networkMapper)

}