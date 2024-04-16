package com.example.translator_kmm.android.translate.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translator_kmm.android.R
import com.example.translator_kmm.android.core.theme.AccentViolet
import com.example.translator_kmm.android.core.theme.DarkGrey
import com.example.translator_kmm.android.core.theme.LightBlueGrey
import com.example.translator_kmm.android.core.theme.TextBlack

@Composable
fun TranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = AccentViolet,
            background = DarkGrey,
            onPrimary = Color.White,
            onBackground = Color.White,
            surface = DarkGrey,
            onSurface = Color.White
        )
    } else {
        lightColorScheme(
            primary = AccentViolet,
            background = LightBlueGrey,
            onPrimary = Color.White,
            onBackground = TextBlack,
            surface = Color.White,
            onSurface = TextBlack
        )
    }
    val sfProText = FontFamily(
        Font(
            resId = R.font.sf_pro_text_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.sf_pro_text_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.sf_pro_text_bold,
            weight = FontWeight.Bold
        ),
    )
    val typography = Typography(
        titleLarge = TextStyle(
            fontFamily = sfProText,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        titleMedium = TextStyle(
            fontFamily = sfProText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        titleSmall = TextStyle(
            fontFamily = sfProText,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = sfProText,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodySmall = TextStyle(
            fontFamily = sfProText,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}