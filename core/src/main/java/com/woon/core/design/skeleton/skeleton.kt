package com.woon.core.design.skeleton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    radius: Dp = 4.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(getSimmerBrush())
    )
}

@Composable
internal fun getSimmerBrush(): Brush {
    val widthOfShadowBrush = 500
    val durationMillis = 1000
    val angleOfAxisY = 270f

    val shimmerColors = listOf(
        Color(0xFFC3C7CC),
        Color(0xFFC3C7CC).copy(alpha = 0.025f),
        Color(0xFFC3C7CC).copy(alpha = 0.005f),
        Color(0xFFC3C7CC).copy(alpha = 0.025f),
        Color(0xFFC3C7CC),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = angleOfAxisY),
    )
}