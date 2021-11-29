package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R


@Composable
fun PlayButton(
    modifier: Modifier
){
    Column(
        modifier = modifier
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val size by infiniteTransition.animateValue(
            initialValue = 200.dp,
            targetValue = 230.dp,
            animationSpec = infiniteRepeatable(
                tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            typeConverter = Dp.VectorConverter
        )
        Surface(
            color = Color(0xFFFFAE02),
            modifier = Modifier
                .size(size = size)
                .border(
                    BorderStroke(
                        10.dp, brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFDE02),
                                Color(0xFFCA7E00),
                            ),
                        )
                    ), shape = RoundedCornerShape(50)
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(50),

                    ),
            shape = RoundedCornerShape(50),
        ){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.play), contentDescription = "Play",
                    modifier = Modifier.size(100.dp).padding(start = 15.dp))
            }
        }
    }
}