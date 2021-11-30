package com.ekotyoo.njawi.presentation.belajar

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun BelajarChapter(
    navController: NavHostController,
    viewModel: BelajarViewModel = hiltViewModel(),
    materiId: String
) {
    viewModel.getMateri(materiId)
    Box (
        modifier = Modifier.pointerInput(Unit){}
            ) {
        Circle()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .pointerInput(Unit) {},
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.id_sinau_njawi),
                contentDescription = "Header Icon",
                Modifier.size(width = 254.dp, height = 122.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier.pointerInput(Unit){}
            ) {
                when (val materiResponse = viewModel.materiState.value) {
                    is Response.Loading -> CircularProgressIndicator()
                    is Response.Success -> HorizontalPager(
                        count = materiResponse.data.chapters?.size ?: 0,
                        modifier = Modifier.height(440.dp)
                    ) { page ->
                        ContentBox(
                            judul = materiResponse.data.chapters?.get(page)?.get("title") as String,
                            contents = materiResponse.data.chapters[page]["contents"] as List<String>,
                            style = Typography.body1
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(1.2f)
                ) {
                    val infiniteTransition = rememberInfiniteTransition()
                    val rotate1 by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 20f,
                        animationSpec = infiniteRepeatable(
                            tween(2000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    val pos1 by infiniteTransition.animateValue(
                        initialValue = 400.dp,
                        targetValue = 150.dp,
                        animationSpec = infiniteRepeatable(
                            tween(2000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        typeConverter = Dp.VectorConverter
                    )
                    Image(
                        modifier = Modifier
                            .offset(-pos1, 300.dp)
                            .size(350.dp)
                            .rotate(rotate1),
                        painter = painterResource(id = R.drawable.lari4),
                        contentDescription = "njawi mascot",
                    )
                }
            }

        }

    }
}
@Composable
fun ContentBox(
    contents: List<String>,
    style: TextStyle,
    judul: String,
){
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.9f)
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
            verticalArrangement = Arrangement.Top,
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
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = judul,
                style = Typography.h1,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                content = {
                    items(
                            count = contents.size
                        ) { item ->
                            Text(
                                text = contents[item],
                                style = style,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                }
            )
        }
    }
}

