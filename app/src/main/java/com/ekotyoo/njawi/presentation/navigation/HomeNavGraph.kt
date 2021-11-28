package com.ekotyoo.njawi.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.HOME_GRAPH_ROUTE
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.HomeScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.squareup.moshi.Moshi

@ExperimentalAnimationApi
fun NavGraphBuilder.homeNavGraph(
    navController: NavController
){
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE
    ){
        composable(
            route = Screen.Home.route
        ){ backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")

            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(User::class.java)
            val userObject = jsonAdapter.fromJson(userJson!!)

            HomeScreen()
        }
        composable(
            route = Screen.Quiz.route
        ){
            PlayQuizScreen(viewModel = PlayQuizViewModel())
        }
        composable(
            route = Screen.Belajar.route
        ){

        }
        composable(
            route = Screen.Profile.route
        ){

        }
    }
}