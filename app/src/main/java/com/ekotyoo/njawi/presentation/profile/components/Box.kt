package com.ekotyoo.njawi.presentation.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R

@Preview
@Composable
fun Preview(){
    expandbox(title = "Achievement")
}

@Composable
fun expandbox(modifier: Modifier = Modifier, title: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 100.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Column() {
        Surface(
            color = Color(0xFFFFAE02),
            modifier = Modifier
                .width(300.dp)
                .border(
                    BorderStroke(
                        6.dp, brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFDE02),
                                Color(0xFFCA7E00),
                            ),
                        )
                    ), shape = RoundedCornerShape(15)
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(15),

                    ),
            shape = RoundedCornerShape(15),
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                    Text(
                        text = title, color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        tint = Color.White,
                        imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded.value) {
                            stringResource(R.string.show_less)
                        } else {
                            stringResource(R.string.show_more)
                        }

                    )
                }
            }
            val isVisible = remember { mutableStateOf(value = false) }
            if (expanded.value) {
                AnimatedVisibility(visible = !isVisible.value) {
                    Row(modifier = Modifier.background(Color.Black).size(100.dp)) {

                    }
                }
            } else {
                AnimatedVisibility(visible = isVisible.value) {
                    Row(modifier = Modifier.background(Color.Black).size(100.dp)) {

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}