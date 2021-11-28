package com.ekotyoo.njawi.presentation.auth

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ekotyoo.njawi.presentation.belajar.BelajarScreen
import com.ekotyoo.njawi.presentation.profile.PhotographerCardPreview
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.ekotyoo.njawi.common.BottomBarScreen
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.profile.PhotographerCard
import com.ekotyoo.njawi.presentation.theme.NjawiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            PhotographerCardPreview(user = user, title = "My Profile")
        }
    }
}

