package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.presentation.quiz.components.NjawiButton
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PlayQuizScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Red, Blue)
                )
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
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
            .fillMaxHeight(0.4f)

    ) {
        words.forEach { word ->
            NjawiButton(text = word.replaceFirstChar { it.uppercase() })
        }
    }
}


@Preview
@Composable
fun NjawiPreview() {
    NjawiTheme() {
        NjawiButton(text = "Login")
    }
}