package com.example.myapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.R

val AlegrayFamilyFontFamily =
    FontFamily(
        Font(R.font.alegreya_bold, FontWeight.Bold),
        Font(R.font.alegreya_semibold, FontWeight.SemiBold),
    )

val AlegraySonsFamily =
    FontFamily(
        Font(R.font.alegreyasans_medium, FontWeight.Medium),
        Font(R.font.alegreyasans_regular, FontWeight.Normal),
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = Green80,
        onPrimary = Green20,
        primaryContainer = Green30,
        onPrimaryContainer = Green90,
        inversePrimary = Green40,
        secondary = DarkGreen80,
        onSecondary = DarkGreen20,
        secondaryContainer = DarkGreen30,
        onSecondaryContainer = DarkGreen90,
        tertiary = Violet80,
        onTertiary = Violet20,
        tertiaryContainer = Violet30,
        onTertiaryContainer = Violet90,
        error = Red80,
        onError = Red20,
        errorContainer = Red30,
        onErrorContainer = Red30,
        background = Grey10,
        onBackground = Grey90,
        surface = GreenGrey30,
        onSurface = GreenGrey80,
        inverseSurface = Grey90,
        inverseOnSurface = Grey10,
        surfaceVariant = GreenGrey30,
        onSurfaceVariant = GreenGrey80,
        outline = GreenGrey80,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Green40,
        onPrimary = Color.White,
        primaryContainer = Green90,
        onPrimaryContainer = Green10,
        inversePrimary = Green80,
        secondary = DarkGreen40,
        onSecondary = Color.White,
        secondaryContainer = DarkGreen90,
        onSecondaryContainer = DarkGreen10,
        tertiary = Violet40,
        onTertiary = Color.White,
        tertiaryContainer = Violet90,
        onTertiaryContainer = Violet10,
        error = Red40,
        onError = Color.White,
        errorContainer = Red90,
        onErrorContainer = Red10,
        background = Grey99,
        onBackground = Grey10,
        surface = GreenGrey90,
        onSurface = GreenGrey30,
        inverseSurface = Grey20,
        inverseOnSurface = Grey95,
        surfaceVariant = GreenGrey90,
        onSurfaceVariant = GreenGrey30,
        outline = GreenGrey50,
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
     */
    )

@Suppress("ktlint:standard:function-naming")
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
