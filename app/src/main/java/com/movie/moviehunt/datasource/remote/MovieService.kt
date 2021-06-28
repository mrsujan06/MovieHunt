package com.movie.moviehunt.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse
}