package com.ekotyoo.njawi.presentation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ekotyoo.njawi.presentation.belajar.BelajarScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.ekotyoo.njawi.common.BottomBarScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.profile.PhotographerCard

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    user: User
){
    Scaffold (
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController, user = user)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Quiz,
        BottomBarScreen.Belajar,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation {
        screens.forEach {
            AddItem(screen = it, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Nav Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BottomNavGraph(navController: NavHostController, user: User) {
    NavHost(navController = navController, startDestination = Screen.Quiz.route) {
        composable(BottomBarScreen.Quiz.route) {
            PlayQuizScreen(viewModel = PlayQuizViewModel())
        }
        composable(BottomBarScreen.Belajar.route) {
            BelajarScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            PhotographerCard(user = user, title = "My Profile")
        }
    }
}
