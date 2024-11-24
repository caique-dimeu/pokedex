package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun adjustColorBrightness(color: Color): Color {
    val hsv = FloatArray(3)
    android.graphics.Color.colorToHSV(color.toArgb(), hsv)
    hsv[2] = if (hsv[2] > 0.5f) hsv[2] - 0.2f else hsv[2] + 0.2f
    return Color(android.graphics.Color.HSVToColor(hsv))
}