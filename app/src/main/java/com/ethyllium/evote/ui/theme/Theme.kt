package com.ethyllium.evote.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val light = lightColorScheme(
    primary = primaryImprovedLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerImprovedLight,
    onPrimaryContainer = Color(0xFF2D1B4B), // Aligned with primary

    secondary = secondaryImprovedLight,
    onSecondary = Color(0xFF000000),
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,

    tertiary = tertiaryImprovedLight,
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFF3F3F3),
    onSurfaceVariant = Color(0xFF444444),

    surfaceContainer = Color(0xFFF5EBF3),
    surfaceContainerLow = surfaceContainerImprovedLowLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight
)

// Dark Theme
val dark = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerImprovedDark,
    onPrimaryContainer = Color(0xFFE2D1FF), // More consistent with system

    secondary = secondaryImprovedDark,
    onSecondary = Color(0xFF000000),
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,

    tertiary = Color(0xFF00F5D4),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF003833),
    onTertiaryContainer = Color(0xFFBAF5EB),

    surface = surfaceImprovedDark,
    onSurface = Color(0xFFF0F0F0),
    surfaceVariant = surfaceVariantImprovedDark,
    onSurfaceVariant = Color(0xFFDADADA)
)

@Immutable
data class ColorFamily(
    val color: Color, val onColor: Color, val colorContainer: Color, val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun EVoteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> dark
        else -> light
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = AppTypography, content = content
    )
}