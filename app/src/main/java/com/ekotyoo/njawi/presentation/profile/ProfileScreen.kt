package com.ekotyoo.njawi.presentation.profile

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.NjawiTheme


@Composable
private fun Greetings(names: List<String> = List(2) { "$it" } ) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            PhotographerCard(title = name)
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),
                        Color(0xFF77549A),
                        Color(0xFF1268C3)
                    ),
                )
            )) {
        Spacer(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth())
        Text(text = "Njawi",
            modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            fontSize = 30.sp,
            color = Color(0xFFFFAE02)
        )
        Spacer(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth())
        Row(
            modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(R.drawable.chad),
                contentDescription = "foto",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(
                            3.dp, brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFFFDE02),
                                    Color(0xFFFF9F00),
                                    Color(0xFFFFAE02),
                                ),
                            )
                        ), shape = CircleShape
                    ),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text("Giga Chad", fontWeight = FontWeight.Medium, color = Color.White, fontSize = 20.sp)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("gigachad@gmail.com",color = Color.White ,style = MaterialTheme.typography.body2)
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier.align(Alignment.CenterHorizontally)) {
            expandbox(title = "Achievement")
            expandbox(title = "LeaderBoard")
        }

    }
}


@Composable
fun expandbox(modifier: Modifier = Modifier, title: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
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
                    Text(text = title, color = Color.White,
                        fontWeight = FontWeight.Bold)
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
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun expandboxreview(){
    NjawiTheme {
        expandbox(title = "Achievement")
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    NjawiTheme {
        PhotographerCard(title = "Achievement")
    }
}