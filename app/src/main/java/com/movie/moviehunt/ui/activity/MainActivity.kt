package com.movie.moviehunt.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.movie.moviehunt.contract.MainContract
import com.movie.moviehunt.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainViewModel = viewModel()
            subscribeObservers(viewModel)

            if (viewModel.currentState.moviesState is MainContract.MovieState.Idle)
                viewModel.setEvent(MainContract.Event.OnFetchPopularMovies)
        }
    }

    private fun subscribeObservers(viewModel: MainViewModel) {

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.onEach { state ->
                when (state.moviesState) {

                    is MainContract.MovieState.Idle -> {
                        displayProgressBar(false)
                    }

                    is MainContract.MovieState.Loading -> {
                        displayProgressBar(true)
                    }

                    is MainContract.MovieState.Success -> {
                        displayProgressBar(false)
                        displayPopularMovies(state.moviesState)
                    }

                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            displayError(it.message)
                        }
                    }
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

    private fun displayPopularMovies(movie: MainContract.MovieState) {
        Timber.d("Movie list : $movie")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}