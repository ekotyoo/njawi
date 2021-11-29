package com.ekotyoo.njawi.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ekotyoo.njawi.presentation.HomeScreen
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.auth.*
import com.ekotyoo.njawi.presentation.quiz.LevelScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavHostContainer(
    navController: NavHostController,
    user: User
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Auth.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.LevelQuiz.route) {
            LevelScreen(navController = navController)
        }

        composable(route = Screen.PlayQuiz.route) {
            PlayQuizScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController, user = user)
        }
    }
}
