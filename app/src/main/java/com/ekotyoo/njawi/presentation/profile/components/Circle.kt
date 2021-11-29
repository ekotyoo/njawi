package com.ekotyoo.njawi.presentation.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun Circle(modifier: Modifier = Modifier){
    val opset = 100f
    Column(modifier.fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFD24074),
                    Color(0xFF77549A),
                    Color(0xFF1268C3)
                ),
            ))) {
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
                topLeft = Offset(x = -opset, y = -opset),
                size = Size(700f,700f)
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
                topLeft = Offset(350f,700f),
                size = Size(1500f,1500f)
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