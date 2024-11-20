package com.example.moviestesttask.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviestesttask.R
import com.example.moviestesttask.model.MovieData
import java.util.Locale

@Composable
fun MovieCardComposable(movieData: MovieData) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 4.dp,
                )
        ) {
            Row {
                Column(
                    horizontalAlignment = Alignment.Start,
					) {

					AsyncImage(
						model = ImageRequest.Builder(LocalContext.current)
							.data("https://image.tmdb.org/t/p/w500${movieData.posterPath}")
							.fallback(R.drawable.no_photo)
							.crossfade(true)
							.build(),
						placeholder = painterResource(R.drawable.ic_launcher_foreground),
						contentDescription = "",
						contentScale = ContentScale.Crop,
						modifier = Modifier.size(48.dp),
					)

                    Text(
                        String.format(Locale.getDefault(), "%.1f", movieData.voteAverage),
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                        maxLines = 1,
                        minLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Column {
                    Text(
                        movieData.title,
                        modifier = Modifier.padding(start = 16.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        movieData.overview,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        style = TextStyle(fontSize = 18.sp),
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 3,
                        minLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Like")
                }

                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Share")
                }
            }
        }
    }

}