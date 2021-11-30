package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Animation(
    images: List<Int>
) {
    val resource: Painter
    val infiniteTransition = rememberInfiniteTransition()
    val animationState = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    )
    if (animationState.value <= 0.125f)
        resource = painterResource(id = images[0])
    else if (animationState.value > 0.125f && animationState.value <= 0.25f)
        resource = painterResource(id = images[1])
    else if (animationState.value > 0.25f && animationState.value <= 0.375f)
        resource = painterResource(id = images[2])
    else if (animationState.value > 0.375f && animationState.value <= 0.5f)
        resource = painterResource(id = images[3])
    else if (animationState.value > 0.5f && animationState.value <= 0.625f)
        resource = painterResource(id = images[4])
    else if (animationState.value > 0.625f && animationState.value <= 0.75f)
        resource = painterResource(id = images[5])
    else if (animationState.value > 0.75f && animationState.value <= 0.875f)
        resource = painterResource(id = images[6])
    else
        resource = painterResource(id = images[7])

    Image(
        painter = resource,
        contentDescription = "",
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(200.dp)
    )
}