package com.example.praktam_2417051014.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(

    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,

    background = AppBackground,
    surface = CardWhite,

    onPrimary = CardWhite,
    onSecondary = CardWhite,

    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun PraktamTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}