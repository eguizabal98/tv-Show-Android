package com.eem.androidcommon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eem.androidcommon.R

@Composable
fun StarRating(score: Double, maxRate: Int) {
    val normalizeScore = (((score * 100) / maxRate) * 5) / 100
    val filledStars = normalizeScore.toInt()
    val halfStars = if (normalizeScore - filledStars < 1.0) 1 else 0
    val outlineStars = 5 - filledStars - halfStars

    Row {
        repeat(filledStars) {
            Image(
                painter = painterResource(id = R.drawable.ic_filled_star),
                contentDescription = stringResource(R.string.filled_star),
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        repeat(halfStars) {
            Image(
                painter = painterResource(id = R.drawable.ic_half_star),
                contentDescription = stringResource(R.string.half_filled_star),
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        repeat(outlineStars) {
            Image(
                painter = painterResource(id = R.drawable.ic_outline_star),
                contentDescription = stringResource(R.string.outline_star),
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}
