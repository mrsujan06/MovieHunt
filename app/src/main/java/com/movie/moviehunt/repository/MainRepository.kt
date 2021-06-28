package com.movie.moviehunt.repository

import com.movie.moviehunt.datasource.database.CacheMapper
import com.movie.moviehunt.datasource.database.MovieDao
import com.movie.moviehunt.datasource.remote.MovieService
import com.movie.moviehunt.datasource.remote.NetworkMapper
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.util.Constants
import com.movie.moviehunt.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getPopularMovies(): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.Loading)

        try {
            val networkMovie = movieService.getPopularMovies(Constants.API_KEY)
            val movies = networkMovie.movieNetworkEntities?.let { networkMapper.mapFromEntityList(it) }

            if (movies != null) {
                for (movie in movies) {
                    movieDao.insert(cacheMapper.mapToEntity(movie))
                }
            }

            val cachedMovies = movieDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedMovies)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}