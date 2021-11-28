package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R


@Preview
@Composable
fun PlayButton(){
    Column() {
        Surface(
            color = Color(0xFFFFAE02),
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
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