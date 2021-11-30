package com.ekotyoo.njawi.presentation.landing.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.presentation.quiz.TextWithShadow
import com.ekotyoo.njawi.presentation.theme.*

@Composable
fun SkipButton(text: String, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
        shape = Shapes.medium,
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp) ,
        modifier = Modifier
            .width(IntrinsicSize.Max)
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