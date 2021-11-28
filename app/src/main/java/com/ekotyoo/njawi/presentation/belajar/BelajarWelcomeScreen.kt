package com.ekotyoo.njawi.presentation.belajar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.*

@Composable
fun belajarWelcomeScreen(
    text: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),
                        Color(0xFF1268C3)
                    ),
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.id_sinau_njawi),
            contentDescription = "icon sinau",
            Modifier.size(width = 254.dp, height = 122.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        box(
            width = 300,
            height = 180,
            text = text,
            style = Typography.body1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.width(600.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fall_inu2),
                contentDescription = "njawi mascot",
                modifier = Modifier.size(height = 300.dp, width = 195.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.bubble_chat),
                contentDescription = "bubble",
                modifier = Modifier.size(105.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview(){
    NjawiTheme {
        belajarWelcomeScreen(text = "Krama Inggil")
    }
}

@Composable
fun box(
    width: Int,
    height: Int,
    text: String,
    style: TextStyle
){
    Box(
        modifier = Modifier
            .size(width = width.dp, height = height.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        resultOutside,
                        resultOutsideBorder
                    )
                ),
                shape = Shapes.medium
            )
            .border(2.dp, color = resultOutsideBorder, shape = Shapes.medium)
            .padding(5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            resultCenter,
                            resultCenterBorder
                        )
                    ),
                    shape = Shapes.medium
                )
                .border(3.dp, color = resultCenterBorder, shape = Shapes.medium)
                .size(width = width.dp, height = height.dp)
        ) {
            Text(
                text = "Halo, perkenalkan aku njawi. Disini kamu akan belajar mengenai bahasa jawa yaitu $text",
                style = style,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}