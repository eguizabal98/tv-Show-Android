package com.eem.androidcommon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eem.androidcommon.R

private val light = Font(R.font.raleway_light, FontWeight.W300)
private val regular = Font(R.font.raleway_regular, FontWeight.W400)
private val medium = Font(R.font.raleway_medium, FontWeight.W500)
private val semibold = Font(R.font.raleway_semibold, FontWeight.W600)

private val craneFontFamily = FontFamily(fonts = listOf(light, regular, medium, semibold))
private val craneBaseStyle = TextStyle(fontFamily = craneFontFamily)

val titleLargeTextStyle = craneBaseStyle.copy(
    color = Color.White,
    fontSize = 30.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = craneFontFamily
)

val titleSmallTextStyle = craneBaseStyle.copy(
    color = Color.White,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = craneFontFamily
)

val typo = Typography(
    displayLarge = TextStyle(fontFamily = craneFontFamily),
    displayMedium = TextStyle(fontFamily = craneFontFamily),
    displaySmall = TextStyle(fontFamily = craneFontFamily),
    headlineLarge = TextStyle(fontFamily = craneFontFamily),
    headlineMedium = TextStyle(fontFamily = craneFontFamily),
    headlineSmall = TextStyle(fontFamily = craneFontFamily),
    titleLarge = titleLargeTextStyle,
    titleMedium = TextStyle(fontFamily = craneFontFamily),
    titleSmall = titleSmallTextStyle,
    bodyLarge = TextStyle(fontFamily = craneFontFamily),
    bodyMedium = TextStyle(fontFamily = craneFontFamily),
    bodySmall = TextStyle(fontFamily = craneFontFamily),
    labelLarge = TextStyle(fontFamily = craneFontFamily),
    labelMedium = TextStyle(fontFamily = craneFontFamily),
    labelSmall = TextStyle(fontFamily = craneFontFamily)
)

val messageTextStyle = typo.headlineLarge.copy(
    color = Color.White,
    fontSize = 15.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = craneFontFamily
)
