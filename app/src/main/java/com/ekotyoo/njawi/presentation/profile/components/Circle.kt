package com.ekotyoo.njawi.presentation.profile.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Circle(modifier: Modifier = Modifier){
    val startawal = 50f
    val startakhir = 50f
    val infiniteTransition = rememberInfiniteTransition()
    val ukuran by infiniteTransition.animateFloat(
        initialValue = 700f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    val posisi1 by infiniteTransition.animateFloat(
        initialValue = startawal,
        targetValue = -startakhir,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    val posisi2 by infiniteTransition.animateFloat(
        initialValue = 700f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),
                        Color(0xFF77549A),
                        Color(0xFF1268C3)
                    ),
                )
            )) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1268C3),
                        Color(0xFF77549A),
                        Color(0xFFD24074),
                    ),
                    start = Offset(500f,800f),
                    end = Offset(0f,0f )
                ),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                topLeft = Offset(x = -posisi1, y = -posisi1),
                size = Size(ukuran,ukuran)
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1268C3),

                        Color(0xFF77549A),
                        Color(0xFFD24074),
                    ),
                    start = Offset(1000f,3000f),
                    end = Offset(500f,500f )
                ),
                startAngle = 90f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(150f,posisi2),
                size = Size(2000f,2000f)
            )
        }
    }
    }


@Preview
@Composable
fun Gradient(modifier: Modifier = Modifier){
    Column(
        modifier
            .size(100.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),

                        Color(0xFF8C4E91),
                        Color(0xFF72549B)
                    ),
                )
            )
    ){

    }
}

@Preview
@Composable
    fun coba(modifier: Modifier = Modifier){



}