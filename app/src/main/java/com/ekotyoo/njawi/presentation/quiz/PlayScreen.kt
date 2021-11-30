package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.Image
import android.animation.TypeConverter
import android.transition.Transition
import android.view.Surface
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.common.navigation.Screen
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.Animation
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import com.ekotyoo.njawi.presentation.quiz.components.PlayButton
import com.ekotyoo.njawi.presentation.theme.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun PlayScreen(
    navController: NavHostController,
    user: com.ekotyoo.njawi.presentation.auth.model.User,
    viewModel: QuizViewModel = hiltViewModel()
){

    var openDialog: Boolean by remember { mutableStateOf(false) }
    Box(
        Modifier.pointerInput(Unit) {}
    )
    {
        Circle()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(R.drawable.sinau),
                contentDescription = "foto",
                modifier = Modifier.padding(top = 50.dp, bottom = 30.dp))
            PlayButton(
                modifier = Modifier,
                    onClick =  {
                        navController.navigate(Screen.LevelQuiz.route)
                    }
            )


        }
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.1f)
                .offset(80.dp, 530.dp)
                .border(
                    2.dp, color = resultOutsideBorder,
                    RoundedCornerShape(15)
                )
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            resultCenter,
                            resultCenterBorder
                        )
                    ),
                    shape = RoundedCornerShape(15)
                ),
            shape = RoundedCornerShape(15),
        ){
            Column(
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
                    )

                    .clickable { openDialog = !openDialog }
                    .border(3.dp, color = resultCenterBorder)
            ) {
                Text(
                    text = "Dulur - Dulur",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 30.sp,
                    style = Typography.h1,
                    textAlign = TextAlign.Center)
            }
        }
                    var posisi by remember { mutableStateOf(600.dp)}
                    var posisi2 by remember { mutableStateOf(600.dp)}
                    val anim by animateDpAsState(
                        targetValue = posisi,
                    animationSpec = spring(Spring.DampingRatioMediumBouncy,Spring.StiffnessLow))
                    val anim1 by animateDpAsState(
                        targetValue = posisi2,
                        animationSpec = spring(Spring.DampingRatioMediumBouncy,Spring.StiffnessLow))
        if (openDialog){
            posisi = 0.dp
            posisi2 = 100.dp
            AnimatedVisibility(visible = openDialog,
                enter = fadeIn(tween(1000)),
                exit = fadeOut(tween(1000)),) {
                leaderBoard(modifier = Modifier.offset(0.dp,anim), user)
            }
            Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "njawi mascot",
                    modifier = Modifier
                        .offset(310.dp, anim1)
                        .size(50.dp)
                        .clickable { openDialog = !openDialog }
                )
        }else {
            posisi = 600.dp
            posisi2 = 600.dp
        }
//        if (openDialog) {
//            AnimatedVisibility(
//                visible = openDialog,
//                enter = slideInVertically(tween(200)) + fadeIn(tween(500)),
//                exit = slideOutVertically(tween(200)) + fadeOut(tween(200)),
//            ) {
//                leaderBoard(modifier = Modifier)
//                Image(
//                    painter = painterResource(id = R.drawable.close),
//                    contentDescription = "njawi mascot",
//                    modifier = Modifier
//                        .size(30.dp)
//                        .clickable {openDialog = ! openDialog}
//                )
//            }
//        }
    }
}

@InternalCoroutinesApi
@Composable
fun leaderBoard(modifier: Modifier,
                user: com.ekotyoo.njawi.presentation.auth.model.User,
                viewModel: QuizViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.getLeaderboards()
    }
    val scrollState = rememberScrollState()
    val Gold: List<Int> = listOf(R.drawable.gold1,R.drawable.gold2,R.drawable.gold3,R.drawable.gold4,R.drawable.gold5,R.drawable.gold6,R.drawable.gold7,R.drawable.gold8,)

    Box(
        modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.6f))) {
        Surface(
            modifier = Modifier
                .padding(50.dp, 100.dp)
                .width(300.dp)
                .height(400.dp)
                .border(
                    2.dp, color = resultOutsideBorder,
                    RoundedCornerShape(15)
                )
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            resultCenter,
                            resultCenterBorder
                        )
                    ),
                    shape = RoundedCornerShape(15)
                ),
            shape = RoundedCornerShape(15),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                resultCenter,
                                resultCenterBorder
                            )
                        ),
                    )
                    .border(3.dp, color = resultCenterBorder)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Dulur - Dulur",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 30.sp,
                    style = Typography.h1,
                    textAlign = TextAlign.Center
                )
                when(val leaderboardResponse = viewModel.leaderboardState.value) {
                    is Response.Loading -> CircularProgressIndicator()
                    is Response.Success -> LazyColumn {
                        item {
                            leaderboardResponse.data.forEach {
                                Row() {
                                    Column(modifier.size(30.dp)) {
                                        Animation(Gold)
                                    }
                                    Text(text = it.name +" : "+ it.score,
                                        color = Color.White,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 20.sp,
                                        style = Typography.h2,
                                        textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}