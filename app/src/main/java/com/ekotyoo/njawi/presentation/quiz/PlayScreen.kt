package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ekotyoo.njawi.presentation.quiz.components.PlayButton
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun PlayScreen(
    navController: NavHostController,
    user: com.ekotyoo.njawi.presentation.auth.model.User,
    viewModel: QuizViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true) {
        viewModel.getLeaderboards()
    }

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
            when(val leaderboardResponse = viewModel.leaderboardState.value) {
                is Response.Loading -> CircularProgressIndicator()
                is Response.Success -> LazyColumn {
                    item {
                        leaderboardResponse.data.forEach {
                            Text(text = it.name + it.score)
                        }
                    }
                }
            }
            
        }
    }
}
