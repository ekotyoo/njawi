package com.ekotyoo.njawi.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.auth.HomeScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.squareup.moshi.Moshi

@ExperimentalAnimationApi
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ) {
        composable(route = Screen.Home.route) { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")

            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(User::class.java)
            val userObject = jsonAdapter.fromJson(userJson!!)

            HomeScreen()
        }
    }
}