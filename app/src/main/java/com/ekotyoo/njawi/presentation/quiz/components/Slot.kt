package com.ekotyoo.njawi.presentation.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.presentation.theme.Blue
import com.ekotyoo.njawi.presentation.theme.Shapes

@Composable
fun Slot(text: String = ""){
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(40.dp)
            .background(
                color = Blue,
                shape = Shapes.medium
            )
            .shadow(
                clip = true,
                shape = Shapes.medium,
                elevation = 3.dp
            )
    )
}