package com.ekotyoo.njawi.presentation

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
import com.ekotyoo.njawi.presentation.auth.HomeScreen
import com.ekotyoo.njawi.presentation.auth.LoginScreen
import com.ekotyoo.njawi.presentation.auth.model.AuthViewModel
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import androidx.compose.ui.graphics.vector.ImageVector

@ExperimentalAnimationApi
@Composable
fun NavHostContainer(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Quiz.route){
            PlayQuizScreen(viewModel = PlayQuizViewModel())
        }
        composable(route = Screen.Auth.route) {
            LoginScreen(authViewModel = AuthViewModel())
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route:String,
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = Icons.Filled.Home,
        route = Screen.Home.route
    ),
    BottomNavItem(
        label = "Quiz",
        icon = Icons.Filled.Search,
        route = Screen.Quiz.route
    ),
    BottomNavItem(
        label = "Auth",
        icon = Icons.Filled.Person,
        route = Screen.Auth.route
    )
)

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = Color(0xFF0F9D58)
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}