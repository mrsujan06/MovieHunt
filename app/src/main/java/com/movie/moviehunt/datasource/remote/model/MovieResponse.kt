package com.movie.moviehunt.datasource.remote.model

import com.movie.moviehunt.datasource.remote.model.MovieNetworkEntity
import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "page")
    var page: Int? = null,
    @Json(name = "results")
    var movieNetworkEntities: List<MovieNetworkEntity>? = null,
    @Json(name = "total_pages")
    var totalPages: Int? = null,
    @Json(name = "total_results")
    var totalResults: Int? = null
)
