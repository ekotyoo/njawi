package com.ekotyoo.njawi.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.auth.AuthScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Auth.route,
        route = AUTH_ROUTE
    ) {
        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }
    }
}