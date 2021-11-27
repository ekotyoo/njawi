package com.ekotyoo.njawi.presentation.belajar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.ekotyoo.njawi.presentation.quiz.components.NjawiButton
import com.ekotyoo.njawi.presentation.theme.*

@Composable
fun ItemView(
    text: String = "Test",
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 134.dp, height = 107.dp)
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
                verticalArrangement = Arrangement.SpaceEvenly,
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
                    .size(width = 134.dp, height = 107.dp)
            ) {
                Text(text = text, style = Typography.button)
            }
        }
    }
}

@Preview
@Composable
fun preview(){
    NjawiTheme {
    }
}
