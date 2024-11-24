package com.example.pokedex.utils

import android.graphics.Bitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun loadBitmapFromUrl(context: android.content.Context, url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .build()
            val result = (loader.execute(request) as SuccessResult).drawable
            (result as? android.graphics.drawable.BitmapDrawable)?.bitmap
        } catch (e: Exception) {
            null
        }
    }
}
