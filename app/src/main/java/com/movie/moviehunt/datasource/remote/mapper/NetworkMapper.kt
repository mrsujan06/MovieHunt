package com.movie.moviehunt.datasource.remote.mapper

import com.movie.moviehunt.datasource.remote.model.MovieNetworkEntity
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<MovieNetworkEntity, Movie> {
    override fun mapFromEntity(entity: MovieNetworkEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Movie): MovieNetworkEntity {
        return MovieNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<MovieNetworkEntity>): List<Movie> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}