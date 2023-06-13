package com.eem.androidcommon.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme

@Composable
fun TopBar(
    title: String,
    backAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Arrow Back",
            Modifier.weight(1f)
        )
        Text(
            text = title,
            Modifier.weight(2f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun TopBar(
    title: String,
    startComposable: () -> Composable,
    endComposable: () -> Composable
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        startComposable()
        Text(text = title, textAlign = TextAlign.Center)
        endComposable()
    }
}

@Preview
@Composable
fun topBarPreview() {
    TvShowAndroidTheme {
        TopBar("Detail") {}
    }
}
