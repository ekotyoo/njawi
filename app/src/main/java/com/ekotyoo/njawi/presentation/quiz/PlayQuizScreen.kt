package com.ekotyoo.njawi.presentation.quiz

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ekotyoo.njawi.presentation.quiz.components.NjawiButton
import com.ekotyoo.njawi.presentation.quiz.components.Slot
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.ekotyoo.njawi.R

@ExperimentalAnimationApi
@Composable
fun PlayQuizScreen(
    headerImg: Int,
    viewModel: PlayQuizViewModel
) {
    val progress: Float by viewModel.progress.observeAsState(initial = 0f)
    val currentAnswer: List<String> by viewModel.currentAnswer
        .observeAsState(initial = mutableListOf())
    val isCorrect: Boolean by viewModel.isCorrect.observeAsState(initial = false)
    val words: List<String> by viewModel.shuffledWords.observeAsState(initial = listOf())
    val density = LocalDensity.current

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Red, Blue)
                    )
                )
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage(image = headerImg)
            TimeLeftIndicator(progress = progress)
            WordsSlot(words = currentAnswer, viewModel = viewModel)
            WordsOption(words = words, viewModel = viewModel)
        }
        AnimatedVisibility(
            visible = isCorrect,
            enter = fadeIn(
                initialAlpha = 0f
            ) ,
            exit = fadeOut()
        ) {
            ResultDialog(viewModel = viewModel)
        }
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
            .fillMaxHeight(0.3f)
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
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Black.copy(alpha = 0.6f))
        )
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .background(color = Orange, shape = Shapes.medium)
                .border(4.dp, color = Black, shape = Shapes.medium)
                .border(16.dp, color = LightOrange, shape = Shapes.medium)
                .padding(16.dp)
                .padding(20.dp)

        ) {
            Text(text = "Hasil", style = Typography.button)
            Text(text = "Skor kamu: 1000", style = Typography.button)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                NjawiButton(text = "Ulangi") {
                    viewModel.restartGame()
                }
                Spacer(Modifier.width(8.dp))
                NjawiButton(text = "Lanjutkan") {
                    viewModel.nextQuestion()
                }
            }
        }
    }
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

@Preview(showBackground = true)
@Composable
fun Preview() {
    NjawiTheme {

    }
}



