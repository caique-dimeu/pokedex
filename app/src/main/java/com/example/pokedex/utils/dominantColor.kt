package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun dominantColor(context: android.content.Context, imageUrl: String): Color {
    return withContext(Dispatchers.IO) {
        try {
            val bitmap = loadBitmapFromUrl(context, imageUrl)
            bitmap?.let {
                val palette = Palette.from(it).generate()
                val dominantColor = palette.getDominantColor(0xFFFFFF)
                adjustColorBrightness(Color(dominantColor))
            } ?: Color.LightGray
        } catch (e: Exception) {
            Color.LightGray
        }
    }
}