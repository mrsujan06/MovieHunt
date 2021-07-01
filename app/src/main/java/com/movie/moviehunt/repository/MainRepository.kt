package com.movie.moviehunt.repository

import com.movie.moviehunt.datasource.database.mapper.CacheMapper
import com.movie.moviehunt.datasource.database.room.MovieDao
import com.movie.moviehunt.datasource.remote.api.MovieService
import com.movie.moviehunt.datasource.remote.mapper.NetworkMapper
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.util.Constants
import com.movie.moviehunt.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        try {
            val networkMovie = movieService.getPopularMovies(Constants.API_KEY)
            val movies = networkMovie.movieNetworkEntities?.let { networkMapper.mapFromEntityList(it) }

            if (movies != null) {
                for (movie in movies) {
                    movieDao.insert(cacheMapper.mapToEntity(movie))
                }
            }

            val cachedMovies = movieDao.get()
            emit(Resource.Success(cacheMapper.mapFromEntityList(cachedMovies)))

        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}