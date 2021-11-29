package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

@Composable
fun LevelScreen(
    navController: NavHostController
){
    Box(
        Modifier.pointerInput(Unit) {}
    ) {
        Circle()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(painter = painterResource(R.drawable.sinau),
                contentDescription = "foto",
                modifier = Modifier.padding(top = 50.dp, bottom = 50.dp))
            LevelButton(
                1, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                2, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                3, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                4, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                5, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                6, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                7, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                8, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                9, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
            LevelButton(
                10, onClick = {
                    navController.navigate(Screen.PlayQuiz.route)
                })
        }
    }
}

