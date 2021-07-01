package com.movie.moviehunt.contract

import com.movie.moviehunt.base.UiEffect
import com.movie.moviehunt.base.UiEvent
import com.movie.moviehunt.base.UiState
import com.movie.moviehunt.model.Movie

class MainContract {
    sealed class Event : UiEvent {
        object OnFetchPopularMovies : Event()
    }
    data class State(
        val moviesState: MovieState
    ) : UiState

    sealed class MovieState {
        object Idle : MovieState()
        object Loading : MovieState()
        data class Success(val movies: List<Movie>) : MovieState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}