package com.movie.moviehunt.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.ui.screen.HomeScreen
import com.movie.moviehunt.ui.viewmodel.MainViewModel
import com.movie.moviehunt.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainViewModel = viewModel()
            HomeScreen(viewModel)
            subscribeObservers(viewModel)
        }
    }

    private fun subscribeObservers(viewModel: MainViewModel) {
        viewModel.dataState.onEach { dataState ->

            when (dataState) {
                is DataState.Success<List<Movie>> -> {
                    displayProgressBar(false)
                    displayPopularMovies(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Timber.e("Error is : $message")
        } else {
            Timber.e("Unknown error")
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        Timber.d("Displaying progress bar ... $isDisplayed")
    }

    private fun displayPopularMovies(movie: List<Movie>) {
        Timber.d("Movie list : $movie")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}