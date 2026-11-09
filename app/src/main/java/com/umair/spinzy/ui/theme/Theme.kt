package com.umair.spinzy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SpinzyColors = darkColorScheme(
    primary = Coral,
    onPrimary = TextHigh,
    secondary = Aqua,
    onSecondary = Midnight,
    tertiary = Sunflower,
    background = Midnight,
    onBackground = TextHigh,
    surface = MidnightRaised,
    onSurface = TextHigh,
    surfaceVariant = Ink,
    onSurfaceVariant = TextMid,
    outline = GlassStroke,
)

@Composable
fun SpinzyTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = SpinzyColors, typography = SpinzyTypography, content = content)
}
