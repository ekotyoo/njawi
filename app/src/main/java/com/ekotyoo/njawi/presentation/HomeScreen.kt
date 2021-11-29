package com.ekotyoo.njawi.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.ekotyoo.njawi.common.BottomBarScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.belajar.BelajarScreen
import com.ekotyoo.njawi.presentation.profile.PhotographerCard
import com.ekotyoo.njawi.presentation.quiz.*


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    routeNavController: NavHostController,
    user: User
){
    val bottomNavController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomBar(navController = bottomNavController)
        }
    ) {
        BottomNavGraph(routeNavController = routeNavController, navController = bottomNavController, user = user)
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
    Surface(
        color = Color(0xFFFFAE02),
        border = BorderStroke(
            10.dp, brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFDE02),
                    Color(0xFFCA7E00),
                ),
            )
        ),
        modifier = Modifier

            .padding(20.dp)
            .border(
                BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(40)
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(40),

                ),
        shape = RoundedCornerShape(40),

        ) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ) {
            screens.forEach {
                AddItem(screen = it, currentDestination = currentDestination, navController = navController)
            }
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
        modifier = Modifier.background(Color(0xFFFFAE02)),
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
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BottomNavGraph(routeNavController: NavHostController, navController: NavHostController, user: User) {
    NavHost(navController = navController, startDestination = Screen.Quiz.route) {
        composable(BottomBarScreen.Quiz.route) {
            PlayScreen(routeNavController)
        }
        composable(BottomBarScreen.Belajar.route) {
            BelajarScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            PhotographerCard(user = user, title = "My Profile")
        }
    }
}

