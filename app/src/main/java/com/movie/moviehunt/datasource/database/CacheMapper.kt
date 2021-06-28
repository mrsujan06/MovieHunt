package com.movie.moviehunt.datasource.database

import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<CacheMovieEntity, Movie> {

    override fun mapFromEntity(entity: CacheMovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Movie): CacheMovieEntity {
        return CacheMovieEntity(
            id = domainModel.id,
            title = domainModel.title,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<CacheMovieEntity>): List<Movie> {
        return entities.map { mapFromEntity(it) }
    }
}