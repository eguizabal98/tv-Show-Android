package com.eem.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.eem.androidcommon.ui.components.LoadingIndicator
import com.eem.androidcommon.ui.components.itemsPaging
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.androidcommon.ui.theme.titleSmallTextStyle
import com.eem.domain.model.tvshow.Filter
import com.eem.domain.model.tvshow.FilterItem
import com.eem.home.ui.HomeViewModel.BaseEvent.OnFilterChange
import com.eem.home.ui.HomeViewModel.BaseEvent.OnShowSnackBar
import com.eem.home.ui.views.TvShowItem

@Composable
internal fun HomeScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit = { _: String, _: SnackbarDuration -> },
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAuthentication: () -> Unit = {}
) {
    val lazyPagingItems = viewModel.getTvShows().collectAsLazyPagingItems()

    viewModel.apply {
        LaunchedEffect(true) {
            viewModel.baseEvent.collect { event ->
                when (event) {
                    is OnShowSnackBar -> {
                        showSnackBar(event.message, SnackbarDuration.Short)
                    }

                    is OnFilterChange -> {
                        lazyPagingItems.refresh()
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getLastFilter()
    }

    val filterList = listOf(
        FilterItem(stringResource(com.eem.home.R.string.top_rated), Filter.TOP_RATED),
        FilterItem(stringResource(com.eem.home.R.string.popular), Filter.POPULAR),
        FilterItem(stringResource(com.eem.home.R.string.on_tv), Filter.ON_TV),
        FilterItem(stringResource(com.eem.home.R.string.airing_today), Filter.AIRING_TODAY)
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 30.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(com.eem.home.R.string.what_do_you_want_to_watch),
                style = titleSmallTextStyle,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(4f),
                textAlign = TextAlign.Start
            )
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(com.eem.home.R.string.filled_account),
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            filterList.forEach {
                Card(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                viewModel.setFilter(it.filterPath.filter)
                            }
                            .background(
                                color = if (it.filterPath.filter == viewModel.uiState.filterSelected) {
                                    MaterialTheme.colorScheme.primaryContainer
                                } else {
                                    Color.Transparent
                                }
                            )
                    ) {
                        Text(
                            text = it.filterLabel,
                            color = if (it.filterPath.filter == viewModel.uiState.filterSelected) {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                Color.Unspecified
                            },
                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                        )
                    }
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            itemsPaging(lazyPagingItems) { tvShow ->
                tvShow?.let {
                    TvShowItem(it) {
                        showSnackBar(
                            lazyPagingItems.itemCount.toString(),
                            SnackbarDuration.Short
                        )
                    }
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingIndicator() }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingIndicator() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = lazyPagingItems.loadState.refresh as LoadState.Error
                        item { Text(text = e.error.localizedMessage ?: "Unknown Error") }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = lazyPagingItems.loadState.append as LoadState.Error
                        item { Text(text = e.error.localizedMessage ?: "Unknown Error") }
                    }
                }
            }
        }
        LoadingIndicator(viewModel.uiState.isLoading)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        HomeScreen()
    }
}
