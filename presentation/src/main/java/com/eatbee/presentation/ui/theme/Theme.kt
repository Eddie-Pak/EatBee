package com.eatbee.presentation.ui.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.eatbee.presentation.R

private val LightColorScheme = lightColorScheme(
    primary = EatBeeColor.PrimaryLight,
    onPrimary = EatBeeColor.OnPrimaryLight,
    primaryContainer = EatBeeColor.PrimaryContainerLight,
    onPrimaryContainer = EatBeeColor.OnPrimaryContainerLight,

    secondary = EatBeeColor.SecondaryLight,
    onSecondary = EatBeeColor.OnSecondaryLight,
    secondaryContainer = EatBeeColor.SecondaryContainerLight,
    onSecondaryContainer = EatBeeColor.OnSecondaryContainerLight,

    tertiary = EatBeeColor.TertiaryLight,
    onTertiary = EatBeeColor.OnTertiaryLight,
    tertiaryContainer = EatBeeColor.TertiaryContainerLight,
    onTertiaryContainer = EatBeeColor.OnTertiaryContainerLight,

    background = EatBeeColor.BackgroundLight,
    onBackground = EatBeeColor.OnBackgroundLight,
    surface = EatBeeColor.SurfaceLight,
    onSurface = EatBeeColor.OnSurfaceLight,
    outline = EatBeeColor.OutlineLight,
    outlineVariant = EatBeeColor.OutlineVariantLight,

    surfaceVariant = EatBeeColor.SurfaceVariant,
    onSurfaceVariant = EatBeeColor.OnSurfaceVariant,

    error = EatBeeColor.ErrorLight,
    onError = EatBeeColor.OnErrorLight,
    errorContainer = EatBeeColor.ErrorContainerLight,
    onErrorContainer = EatBeeColor.OnErrorContainerLight
)

private val DarkColorScheme = darkColorScheme(
    primary = EatBeeColor.PrimaryDark,
    onPrimary = EatBeeColor.OnPrimaryDark,
    primaryContainer = EatBeeColor.PrimaryContainerDark,
    onPrimaryContainer = EatBeeColor.OnPrimaryContainerDark,

    secondary = EatBeeColor.SecondaryDark,
    onSecondary = EatBeeColor.OnSecondaryDark,
    secondaryContainer = EatBeeColor.SecondaryContainerDark,
    onSecondaryContainer = EatBeeColor.OnSecondaryContainerDark,

    tertiary = EatBeeColor.TertiaryDark,
    onTertiary = EatBeeColor.OnTertiaryDark,
    tertiaryContainer = EatBeeColor.TertiaryContainerDark,
    onTertiaryContainer = EatBeeColor.OnTertiaryContainerDark,

    background = EatBeeColor.BackgroundDark,
    onBackground = EatBeeColor.OnBackgroundDark,
    surface = EatBeeColor.SurfaceDark,
    onSurface = EatBeeColor.OnSurfaceDark,
    outline = EatBeeColor.OutlineDark,
    outlineVariant = EatBeeColor.OutlineVariantDark,

    surfaceVariant = EatBeeColor.SurfaceVariant,
    onSurfaceVariant = EatBeeColor.OnSurfaceVariant,

    error = EatBeeColor.ErrorDark,
    onError = EatBeeColor.OnErrorDark,
    errorContainer = EatBeeColor.ErrorContainerDark,
    onErrorContainer = EatBeeColor.OnErrorContainerDark
)

@Composable
fun EatBeeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val window = LocalActivity.current?.window

    DisposableEffect(darkTheme) {
        window?.setBackgroundDrawableResource(
            if (darkTheme) R.color.background_dark else R.color.background_light
        )
        onDispose {}
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EatBeeTypography,
        content = content
    )
}
