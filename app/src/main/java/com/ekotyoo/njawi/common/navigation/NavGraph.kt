package com.ekotyoo.njawi.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.HomeScreen
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.AuthScreen
import com.ekotyoo.njawi.presentation.quiz.LevelScreen
import com.squareup.moshi.Moshi

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
            AuthScreen(navController = navController)
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
