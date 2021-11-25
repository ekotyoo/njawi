package com.ekotyoo.njawi.presentation.auth

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.Shapes
import com.ekotyoo.njawi.presentation.theme.Typography

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    text: String = "Sign Up with Google",
    loadingText: String = "Creating Account...",
    icon: Int = R.drawable.ic_google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color(0xFFFFDB02),
    backgroundColor: Color = Color(0xFFFFB002),
    textStyle: TextStyle = Typography.body2,
    progressIndicatorColor: Color = Color.White,
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
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clicked) loadingText else text, color = Color.White, style = textStyle)
            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
        }
    }
}

@Composable
@Preview
private fun GoogleButtonPreview() {
    GoogleButton(
        text = "Sign Up with Google",
        loadingText = "Creating Account..."
    ) {}
}