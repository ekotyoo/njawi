package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import com.ekotyoo.njawi.presentation.quiz.components.PlayButton
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

@Composable
fun PlayScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(R.drawable.sinau),
            contentDescription = "foto",
            modifier = Modifier.padding(top = 50.dp, bottom = 100.dp))
        PlayButton()
    }
}



@Preview
@Composable
fun HalamanPlay(){
    NjawiTheme {
        Box {
            Circle()
            PlayScreen()
        }
    }
}