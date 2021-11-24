package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ekotyoo.njawi.presentation.quiz.components.NjawiButton
import com.ekotyoo.njawi.presentation.quiz.components.Slot
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.ekotyoo.njawi.R;

@Composable
fun PlayQuizScreen(headerImg: Int) {
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
        TimeLeftIndicator(progress = 80f)
        WordsSlot(words = words)
        WordsOption(words = words)
    }
}


const val sentence = "bapak sare kula adus nanging dereng panjenengan menika"
val words = sentence.split(" ").shuffled()

@Composable
fun WordsOption(words: List<String>) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)

    ) {
        words.forEach { word ->
            NjawiButton(text = word.replaceFirstChar { it.uppercase() })
        }
    }
}

@Composable
fun WordsSlot(words: List<String>) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
        words.forEach {
            Slot()
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



