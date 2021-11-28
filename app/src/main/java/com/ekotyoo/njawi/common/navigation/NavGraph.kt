package com.ekotyoo.njawi.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.HomeScreen
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.AuthScreen
import com.squareup.moshi.Moshi

@ExperimentalAnimationApi
@Composable
fun NavHostContainer(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route,
    ) {
        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(route = Screen.Home.route) { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")

            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(User::class.java)
            val userObject = jsonAdapter.fromJson(userJson!!)

            HomeScreen(navController)
        }
    }
}
