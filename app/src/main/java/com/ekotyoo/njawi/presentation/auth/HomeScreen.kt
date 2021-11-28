package com.ekotyoo.njawi.presentation.auth

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.common.navigation.Screen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.belajar.BelajarScreen
import com.ekotyoo.njawi.presentation.profile.ProfileScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
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

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Quiz : BottomBarScreen(
        route = Screen.Quiz.route,
        title = "Quiz",
        icon = Icons.Default.Face
    )

    object Belajar : BottomBarScreen(
        route = Screen.Belajar.route,
        title = "Belajar",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )
}

@ExperimentalAnimationApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Quiz.route) {
        composable(BottomBarScreen.Quiz.route) {
            PlayQuizScreen(viewModel = PlayQuizViewModel())
        }
        composable(BottomBarScreen.Belajar.route) {
            BelajarScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}
