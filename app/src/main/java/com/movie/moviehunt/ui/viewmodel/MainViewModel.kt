package com.movie.moviehunt.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.movie.moviehunt.base.BaseViewModel
import com.movie.moviehunt.contract.MainContract
import com.movie.moviehunt.repository.MainRepository
import com.movie.moviehunt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            moviesState = MainContract.MovieState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchPopularMovies -> {
                fetchPopularMovies()
            }
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            mainRepository.getPopularMovies()
                .onStart { emit(Resource.Loading) }
                .onEach {
                    when (it) {

                        is Resource.Loading -> {
                            // Set State
                            setState { copy(moviesState = MainContract.MovieState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(moviesState = MainContract.MovieState.Idle) }
                        }
                        is Resource.Success -> {
                            Timber.d("Movies : ${it.data}")
                            // Set State
                            setState { copy(moviesState = MainContract.MovieState.Success(movies = it.data)) }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }

                    }

                }.launchIn(viewModelScope)

        }
    }
}
