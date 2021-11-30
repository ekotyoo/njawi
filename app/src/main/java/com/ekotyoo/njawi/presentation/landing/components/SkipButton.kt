package com.ekotyoo.njawi.presentation.landing.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.quiz.TextWithShadow
import com.ekotyoo.njawi.presentation.theme.*

@Composable
fun SkipButton(
    modifier: Modifier = Modifier,
    text: String = "Lewati",
    shape: Shape = Shapes.medium,
    borderColor: Color = Color(0xFFFFDB02),
    backgroundColor: Color = Color(0xFFFFB002),
    textStyle: TextStyle = Typography.body2,
    onClicked: () -> Unit
) {
    val clicked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .clickable {
                onClicked()
            },
        shape = shape,
        border = BorderStroke(width = 3.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}