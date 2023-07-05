package com.eem.showdetails.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.eem.androidcommon.ui.components.LoadingIndicator
import com.eem.androidcommon.ui.components.TopBar
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.domain.model.tvshow.TvShowDetails
import com.eem.domain.model.tvshow.TvShowGenre
import com.eem.showdetails.R
import com.eem.showdetails.ui.details.ShowDetailsViewModel.BaseEvent.OnShowSnackBar
import com.eem.showdetails.ui.details.ShowDetailsViewModel.UIState

@Composable
internal fun showDetailsScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit = { _: String, _: SnackbarDuration -> },
    viewModel: ShowDetailsViewModel = hiltViewModel(),
    navigateToHome: () -> Unit = {}
) {
    val context = LocalContext.current
    viewModel.apply {
        LaunchedEffect(true) {
            viewModel.baseEvent.collect { event ->
                when (event) {
                    is OnShowSnackBar -> {
                        showSnackBar(event.message, SnackbarDuration.Short)
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.checkShowID()
    }

    ShowDetailsContent(viewModel.uiState)
}

@Composable
private fun ShowDetailsContent(uiState: UIState, modifier: Modifier = Modifier) {
    uiState.tvShowDetails?.let { ShowDetails(tvShowDetails = it, modifier = modifier) }
    LoadingIndicator(uiState.isLoading)
}

@Composable
private fun ShowDetails(tvShowDetails: TvShowDetails, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopBar(tvShowDetails.name) {
        }
        OverlappingImages(
            principalUrl = tvShowDetails.getBackdropUrl(),
            secondaryUrl = tvShowDetails.getPosterUrl(),
            title = tvShowDetails.name
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_calendar),
                    contentDescription = "calendar icon"
                )
                Text(text = tvShowDetails.firstAirDate, modifier = Modifier.padding(start = 10.dp))
            }
            if (tvShowDetails.episodeRunTime.isNotEmpty()) {
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .height(20.dp) // fill the max height
                        .width(1.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_access_time),
                        contentDescription = "calendar icon"
                    )
                    Text(
                        text = "${tvShowDetails.episodeRunTime.first()} min.",
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            if (tvShowDetails.genres.isNotEmpty()) {
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .height(20.dp) // fill the max height
                        .width(1.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_movie),
                        contentDescription = "calendar icon"
                    )
                    Text(
                        text = tvShowDetails.genres.first().name,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }

        Text(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 20.dp,
                bottom = 15.dp
            ),
            text = "Summary",
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            modifier = Modifier.padding(16.dp),
            text = tvShowDetails.overview
        )
    }
}

@Composable
private fun OverlappingImages(principalUrl: String, secondaryUrl: String, title: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val imageA = rememberAsyncImagePainter(
            placeholder = painterResource(id = com.eem.androidcommon.R.drawable.ic_filled_star),
            model = principalUrl,
            error = painterResource(id = com.eem.androidcommon.R.drawable.ic_filled_star)
        )
        val imageB = rememberAsyncImagePainter(
            placeholder = painterResource(id = com.eem.androidcommon.R.drawable.ic_filled_star),
            model = secondaryUrl
        )
        val (imageRef, smallImageRef, titleRef) = createRefs()

        Card(
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(300.dp)
        ) {
            Image(
                painter = imageA,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Card(
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(smallImageRef) {
                    top.linkTo(imageRef.bottom)
                    bottom.linkTo(imageRef.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            Image(
                painter = imageB,
                contentDescription = null,
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(imageRef.bottom, margin = 15.dp)
                    start.linkTo(smallImageRef.end, margin = 10.dp)
                    end.linkTo(imageRef.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        ShowDetailsContent(
            UIState(
                isLoading = false,
                tvShowDetails = TvShowDetails(
                    id = 0,
                    backdropPath = "/nGfjgUlES2WuYrHXNNF4fbGe2Eq.jpg",
                    episodeRunTime = listOf(10),
                    firstAirDate = "10-1-2022",
                    genres = listOf(TvShowGenre(0, "Action")),
                    lastAirDate = "2010",
                    name = "Breaking Bad",
                    overview = LoremIpsum(50).values.joinToString { " " },
                    popularity = 5.0,
                    posterPath = "/qkoM63HDuCOSwxGfb0pljrgns9I.jpg",
                    seasons = listOf(),
                    voteAverage = 1.0,
                    voteCount = 10
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
