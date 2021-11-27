package com.ekotyoo.njawi.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.LoginScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
       composable(
           route = Screen.Login.route
       ) {
           LoginScreen(navController = navController)
       }
        composable(
            route = Screen.Quiz.route
        ){
            PlayQuizScreen(headerImg = R.drawable.img_susun_kalimat, PlayQuizViewModel())
        }
    }
}