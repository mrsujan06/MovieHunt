package com.movie.moviehunt.ui.screen

import androidx.compose.runtime.Composable
import com.movie.moviehunt.ui.viewmodel.MainStateEvent
import com.movie.moviehunt.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    viewModel.setStateEvent(MainStateEvent.GetMovieEvents)
}







