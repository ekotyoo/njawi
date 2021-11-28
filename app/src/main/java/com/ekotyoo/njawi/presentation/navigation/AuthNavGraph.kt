package com.ekotyoo.njawi.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ekotyoo.njawi.presentation.AUTH_GRAPH_ROUTE
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.AuthScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
){
    navigation(
        startDestination = Screen.Auth.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.Auth.route
        ){
            AuthScreen(navController = navController)
        }
    }
}