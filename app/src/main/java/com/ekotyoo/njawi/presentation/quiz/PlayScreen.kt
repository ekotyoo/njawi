package com.ekotyoo.njawi.presentation.quiz

import android.animation.TypeConverter
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import com.ekotyoo.njawi.presentation.quiz.components.PlayButton
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

@Composable
fun PlayScreen(
    navController: NavHostController
){
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
    }
}
