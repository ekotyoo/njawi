package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.presentation.quiz.components.NjawiButton
import com.ekotyoo.njawi.presentation.quiz.components.Slot
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.profile.components.Circle

@ExperimentalAnimationApi
@Composable
fun PlayQuizScreen(
    viewModel: PlayQuizViewModel = hiltViewModel(),
    navController: NavHostController,
    quizId: String,
    user: com.ekotyoo.njawi.presentation.auth.model.User
) {
    val progress: Float by viewModel.progress.observeAsState(initial = 0f)
    val question: String by viewModel.question.observeAsState(initial = "")
    val currentAnswer: List<String> by viewModel.currentAnswer
        .observeAsState(initial = mutableListOf())
    val isDone: Boolean by viewModel.isDone.observeAsState(initial = false)
    val words: List<String> by viewModel.shuffledWords.observeAsState(initial = listOf())
    val runImages: List<Int> = listOf(R.drawable.lari1, R.drawable.lari2, R.drawable.lari3, R.drawable.lari4, R.drawable.lari5, R.drawable.lari6, R.drawable.lari7, R.drawable.lari8)
    val deadImages: List<Int> = listOf(R.drawable.dead_1, R.drawable.dead_2, R.drawable.dead_3, R.drawable.dead_4, R.drawable.dead_5, R.drawable.dead_6, R.drawable.dead_6, R.drawable.dead_7)
    val isCorrect: Boolean by viewModel.isCorrect.observeAsState(initial = false)
    val state = viewModel.state.value
    val isTimesUp = viewModel.isTimesUp.value


    Box (
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {}
            ) {
        Circle()
        when {
            state.error.isNotBlank() -> {
                Text(text = state.error)
            }
            state.isLoading -> {
                CircularProgressIndicator()
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isDone) {
                        HeaderAnimation(deadImages)
                    } else {
                        HeaderAnimation(runImages)
                    }
                    ScoreIndicator(viewModel = viewModel)
                    TimeLeftIndicator(progress = progress)
                    Text(text = question)
                    WordsSlot(words = currentAnswer, viewModel = viewModel)
                    WordsOption(words = words, viewModel = viewModel)
                }
                AnimatedVisibility(
                    visible = isDone,
                    enter = fadeIn(
                        initialAlpha = 0f
                    ) ,
                    exit = fadeOut()
                ) {
                    ResultDialog(viewModel = viewModel, navController = navController, user = user)
                }
                AnimatedVisibility(
                    visible = isTimesUp,
                    enter = slideInVertically(tween(200)) + fadeIn(tween(500)),
                    exit = slideOutVertically(tween(200)) + fadeOut(tween(200)),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Black.copy(alpha = 0.6f))
                            .pointerInput(Unit) {}
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.2f)
                                .background(color = Orange)
                                .pointerInput(Unit) {}
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Mantap!!")
                                Text(text = viewModel.correctWords.value!!.joinToString(separator = " ").capitalize())
                                Text(text = viewModel.quiz.questions!![viewModel.currentIndex.value!!].targetSentence.capitalize())
                                NjawiButton(text = "Lanjut") {
                                    viewModel.nextQuestion()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreIndicator(viewModel: PlayQuizViewModel) {
    val correct: Int by viewModel.correctQuestions.observeAsState(initial = 0)
    val total = viewModel.quiz.questions!!.size
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            ) {
        Text(text = "Benar: $correct", style =  Typography.button, color = White)
        Text(text = "Total: $total", style =  Typography.button, color = White)
    }
}

@Composable
fun WordsOption(words: List<String>, viewModel: PlayQuizViewModel) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        words.forEach { word ->
            NjawiButton(text = word.replaceFirstChar { it.uppercase() }, onClick = {
                viewModel.addAnswer(word)
            })
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WordsSlot(words: List<String>, viewModel: PlayQuizViewModel) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
        words.forEach { word ->
            Slot(text = word) {
                viewModel.removeAnswer(word)
            }
        }
    }
}

@Composable
fun ResultDialog(
    viewModel: PlayQuizViewModel,
    navController: NavHostController,
    user: User
) {
    val totalScore: Int = viewModel.totalScore.value!!
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .pointerInput(Unit) {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.6f))
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                resultOutside,
                                resultOutsideBorder
                            )
                        ),
                        shape = Shapes.medium
                    )
                    .border(3.dp, color = resultOutsideBorder, shape = Shapes.medium)
                    .padding(20.dp, 40.dp, 20.dp, 20.dp)
            ) {
                Column (
                    verticalArrangement = Arrangement.SpaceEvenly,
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
                        .padding(20.dp)

                ) {
                    Text(text = "Skor kamu: $totalScore", style = Typography.button, color = Color.White)
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        NjawiButton(text = "Ulangi") {
                            viewModel.restartGame()
                        }
                        Spacer(Modifier.width(8.dp))
                        NjawiButton(text = "Lanjut") {
                            viewModel.addUserResultToFirestore(username = user.email.toString().split("@").first())
                            navController.popBackStack()
                        }
                    }
                }
            }
            Text(text = "Hasil", style = Typography.button.copy(fontSize = 24.sp), color = Color.White, modifier = Modifier
                .align(
                    Alignment.TopCenter
                )
                .padding(top = 4.dp))
        }
    }
}

@Composable
fun TextWithShadow(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        color = Color.DarkGray,
        style = Typography.button,
        modifier = modifier
            .offset(
                x = 2.dp,
                y = 2.dp
            )
            .alpha(0.75f)
    )
    Text(
        text = text,
        color = Color.White,
        style = Typography.button,
        modifier = modifier
    )
}

@Composable
fun HeaderImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(200.dp)
    )
}

@Composable
fun HeaderAnimation(
    images: List<Int>
) {
    val resource: Painter
    val infiniteTransition = rememberInfiniteTransition()
    val animationState = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    )
    if (animationState.value <= 0.125f)
        resource = painterResource(id = images[0])
    else if (animationState.value > 0.125f && animationState.value <= 0.25f)
        resource = painterResource(id = images[1])
    else if (animationState.value > 0.25f && animationState.value <= 0.375f)
        resource = painterResource(id = images[2])
    else if (animationState.value > 0.375f && animationState.value <= 0.5f)
        resource = painterResource(id = images[3])
    else if (animationState.value > 0.5f && animationState.value <= 0.625f)
        resource = painterResource(id = images[4])
    else if (animationState.value > 0.625f && animationState.value <= 0.75f)
        resource = painterResource(id = images[5])
    else if (animationState.value > 0.75f && animationState.value <= 0.875f)
        resource = painterResource(id = images[6])
    else
        resource = painterResource(id = images[7])

    Image(
        painter = resource,
        contentDescription = "",
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(200.dp)
    )
}

@Composable
fun TimeLeftIndicator(progress: Float) {
    Row {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .background(Orange, Shapes.medium)
                .border(3.dp, Black, Shapes.medium)
                .padding(3.dp)
                .border(5.dp, LightOrange, Shapes.medium)
        )  {
            Icon(painter = painterResource(
                id = R.drawable.ic_baseline_alarm_24),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress / 100f)
                    .height(40.dp)
                    .background(Orange, Shapes.medium)
                    .border(3.dp, Black, Shapes.medium)
                    .padding(3.dp)
                    .border(5.dp, LightOrange, Shapes.medium)
            )
        }
    }
}



