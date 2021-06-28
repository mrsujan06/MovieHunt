package com.movie.moviehunt.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.repository.MainRepository
import com.movie.moviehunt.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState =
        MutableStateFlow<DataState<List<Movie>>>(DataState.Success(emptyList()))
    val dataState: StateFlow<DataState<List<Movie>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch(Dispatchers.IO) {
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