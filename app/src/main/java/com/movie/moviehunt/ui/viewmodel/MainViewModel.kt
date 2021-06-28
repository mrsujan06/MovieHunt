package com.movie.moviehunt.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.repository.MainRepository
import com.movie.moviehunt.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel
@ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Movie>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Movie>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetMovieEvents -> {
                    mainRepository.getPopularMovies()
                        .onEach { dataState ->
                            _dataState.value = dataState
                            Timber.d("${_dataState.value}")
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class MainStateEvent {

    object GetMovieEvents : MainStateEvent()
}