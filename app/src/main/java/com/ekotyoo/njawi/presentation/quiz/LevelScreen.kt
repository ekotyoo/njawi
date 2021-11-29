package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.common.navigation.Screen
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun LevelScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    navController: NavHostController
){
    Box(
        Modifier.pointerInput(Unit) {}
    ) {
        Circle()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(R.drawable.sinau),
                contentDescription = "foto",
                modifier = Modifier.padding(top = 50.dp, bottom = 50.dp))

            when(val quizzesResponse = viewModel.quizzesState.value) {
                is Response.Loading -> CircularProgressIndicator()
                is Response.Success -> LazyColumn {
                    items(quizzesResponse.data.size) {
                        quizzesResponse.data.forEach {
                            LevelButton(it.level as String) {
                                navController.navigate(Screen.PlayQuiz.route.replace("{id}", it.id!!))
                            }
                        }
                    }
                }
            }
        }
    }
}

