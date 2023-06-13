package com.eem.home.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.eem.androidcommon.ui.components.StarRating
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.domain.model.tvshow.TvShowInfo
import com.eem.home.R

@Composable
fun TvShowItem(tvShow: TvShowInfo, itemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                itemClick()
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            tvShow.posterImage?.let { url ->
                val image = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w200$url")
                Image(
                    painter = image,
                    contentDescription = buildString {
                        append(stringResource(R.string.poster_for))
                        append(tvShow.name)
                    },
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tvShow.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRating(tvShow.score, 10)
                    Text(modifier = Modifier.padding(start = 10.dp), text = tvShow.score.toString())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        TvShowItem(
            TvShowInfo(1, "TvShow Test", 5.0, "10", "", "", "Description")
        ) {}
    }
}
