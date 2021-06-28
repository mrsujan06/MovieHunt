package com.movie.moviehunt.di

import com.movie.moviehunt.datasource.database.CacheMapper
import com.movie.moviehunt.datasource.database.MovieDao
import com.movie.moviehunt.datasource.remote.MovieService
import com.movie.moviehunt.datasource.remote.NetworkMapper
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