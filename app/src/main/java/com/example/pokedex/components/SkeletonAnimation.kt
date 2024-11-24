package com.example.pokedex.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SkeletonAnimation(
    modifier: Modifier = Modifier,
    durationMillis: Int = 2000
) {
    val colors = listOf(
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.4f),
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.8f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.4f),
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.8f),
        Color.LightGray.copy(alpha = 1.0f)
    )

    val transition = rememberInfiniteTransition(label = "")

    val color = transition.animateColor(
        initialValue = colors[0],
        targetValue = colors[colors.size - 1],
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color.value)
    ) { }
}
