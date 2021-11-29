package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.NjawiTheme


@Composable
fun LevelButton(
    level: String,
    onClick: () -> Unit
){
    Column(
    ) {
        Surface(
            color = Color(0xFFFFAE02),
            modifier = Modifier
                .height(70.dp)
                .width(250.dp)
                .border(
                    BorderStroke(
                        6.dp, brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFDE02),
                                Color(0xFFCA7E00),
                            ),
                        )
                    ), shape = RoundedCornerShape(40)
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(40),
                )
                .clickable { onClick() },
            shape = RoundedCornerShape(40),
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text ="Level "+level, fontFamily = FontFamily(Font(R.font.luckiest_guy, FontWeight.W400)),
                    fontSize = 30.sp,
                    color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
