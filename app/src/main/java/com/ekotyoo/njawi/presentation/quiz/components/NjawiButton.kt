package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.presentation.quiz.TextWithShadow
import com.ekotyoo.njawi.presentation.theme.*

@Composable
fun NjawiButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
        shape = Shapes.medium,
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp) ,
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .border(3.dp, Black, Shapes.medium)
            .padding(3.dp)
            .border(5.dp, LightOrange, Shapes.medium)
    ) {
        Box {
            TextWithShadow(
                text = text,
                modifier = Modifier
            )
        }
    }
}