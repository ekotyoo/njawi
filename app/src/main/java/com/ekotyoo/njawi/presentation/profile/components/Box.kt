package com.ekotyoo.njawi.presentation.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.data.dto.AchievementDto
import com.ekotyoo.njawi.presentation.profile.ProfileViewModel


@Composable
fun Expandbox(
    modifier: Modifier = Modifier,
    title: String,
    items: List<AchievementDto>
) {
    val koinImages: List<Int> = listOf(R.drawable.koin1,R.drawable.koin2,R.drawable.koin3,R.drawable.koin4,R.drawable.koin5,R.drawable.koin6)
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
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
            Row(modifier = Modifier.padding(start = 24.dp, top = 15.dp, end = 24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)

                ) {
                    Text(
                        text = title, color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 21.sp
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

                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                //a
                val isVisible = remember { mutableStateOf(value = false) }
                if (expanded.value) {
                    AnimatedVisibility(visible = !isVisible.value) {
                        Column(
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        ) {
                            items.forEach {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    koin(koinImages)
                                    Spacer(modifier = Modifier.width(0.dp))
                                    Text(
                                        text = it.description,
                                        color = Color.White,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun koin(
    images: List<Int>
    ) {
        val resource: Painter
        val infiniteTransition = rememberInfiniteTransition()
        val animationState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 6f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = LinearEasing
                )
            )
        )
        if (animationState.value <= 1f)
            resource = painterResource(id = images[0])
        else if (animationState.value > 1f && animationState.value <= 2f)
            resource = painterResource(id = images[1])
        else if (animationState.value > 2f && animationState.value <= 3f)
            resource = painterResource(id = images[2])
        else if (animationState.value > 3f && animationState.value <= 4f)
            resource = painterResource(id = images[3])
        else if (animationState.value > 4f && animationState.value <= 5f)
            resource = painterResource(id = images[4])
        else
            resource = painterResource(id = images[5])

        Image(
            painter = resource,
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(30.dp)
                .shadow(
                    elevation = 70.dp,
                    shape = RoundedCornerShape(50),
                )
        )
}