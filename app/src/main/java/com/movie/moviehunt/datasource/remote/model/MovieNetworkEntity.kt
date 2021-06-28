package com.movie.moviehunt.datasource.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieNetworkEntity(
    @Json(name = "id")
    var id: Int,
    @Json(name = "original_title")
    var title: String,
    @Json(name = "poster_path")
    var image: String
)
