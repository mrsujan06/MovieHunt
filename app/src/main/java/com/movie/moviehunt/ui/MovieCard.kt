package com.movie.moviehunt.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.moviehunt.model.Movie
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MovieCard(modifier: Modifier = Modifier, movie: Movie) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = modifier
            .width(135.dp)
            .height(200.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            val imageLink = "https://image.tmdb.org/t/p/w500/${movie.image}"

            CoilImage(
                data = imageLink,
                contentDescription = movie.title,
                contentScale = ContentScale.Fit,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun PreviewDarkTheme() {
    MovieCard(movie = Movie(1, "Luca", "exxx"))
}