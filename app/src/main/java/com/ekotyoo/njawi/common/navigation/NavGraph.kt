package com.ekotyoo.njawi.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ekotyoo.njawi.presentation.HomeScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.auth.*
import com.ekotyoo.njawi.presentation.belajar.BelajarChapter
import com.ekotyoo.njawi.presentation.belajar.BelajarScreen
import com.ekotyoo.njawi.presentation.quiz.LevelScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalPagerApi
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

        composable(route = Screen.PlayQuiz.route) { backstackEntry ->
            backstackEntry.arguments?.getString("id")?.let {
                PlayQuizScreen(
                    navController = navController,
                    quizId = it
                )
            }
        }

        composable(
            route = Screen.BelajarDetail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backstackEntry ->
            backstackEntry.arguments?.getString("id")?.let {
                BelajarChapter(
                    navController = navController,
                    materiId = it
                )
            }
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController, user = user)
        }

    }
}
