package com.movie.moviehunt.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.movie.moviehunt.R
import com.movie.moviehunt.model.Movie
import com.movie.moviehunt.ui.MovieCard
import com.movie.moviehunt.ui.viewmodel.MainViewModel
import timber.log.Timber

//@Composable
//fun HomeScreen(viewModel: MainViewModel) {
//
//
//    val collectAsState: State<Result<List<Movie>>> = viewModel.uiState.collectAsState()
//    HomeScreenScaffold(collectAsState.value)
//}
//
//@Composable
//fun HomeScreenScaffold(state: DataState<List<Movie>>) {
//    Scaffold(
//        content = { paddingValues ->
//            when (state) {
//                DataState.Loading -> {
//                    HomeScreenLoader(paddingValues)
//                }
//
//                else -> {
//                    HomeScreenContent(paddingValues = paddingValues, state = state)
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun HomeScreenLoader(paddingValues: PaddingValues) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(paddingValues)
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier
//                .wrapContentSize()
//                .align(Alignment.Center)
//        )
//    }
//}
//
//
//@Composable
//fun HomeScreenContent(paddingValues: PaddingValues, state: DataState<List<Movie>>) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(paddingValues)
//            .padding(horizontal = 16.dp)
//    ) {
//    }
//
////  BrowsePopularMovies(movies = s)
//
//}
//
//
//@Composable
//private fun BrowsePopularMovies(movies: List<Movie>) {
//
//    Text(
//        text = stringResource(R.string.popular_title),
//        style = MaterialTheme.typography.h1,
//        color = MaterialTheme.colors.onPrimary,
//        modifier = Modifier
//            .paddingFromBaseline(32.dp)
//    )
//
//    Spacer(Modifier.height(16.dp))
//
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(movies) {
//            MovieCard(movie = it)
//        }
//    }
//
//}
//
//@Composable
//private fun Render(dataState: DataState<List<Movie>>) {
//    when (dataState) {
//        is DataState.Success<List<Movie>> -> {
//            BrowsePopularMovies(dataState.data)
//        }
//        else -> {
//            Timber.e("Error Occurred when Subscribing")
//        }
//    }
//}
