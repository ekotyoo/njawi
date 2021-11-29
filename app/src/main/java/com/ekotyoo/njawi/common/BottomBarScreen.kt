package com.ekotyoo.njawi.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ekotyoo.njawi.common.navigation.Screen

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
