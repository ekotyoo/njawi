package com.ekotyoo.njawi.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ekotyoo.njawi.common.navigation.Screen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Book
import compose.icons.fontawesomeicons.solid.Gamepad

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Quiz : BottomBarScreen(
        route = Screen.Quiz.route,
        title = "Quiz",
        icon = Icons.Default.Gamepad
    )

    object Belajar : BottomBarScreen(
        route = Screen.Belajar.route,
        title = "Belajar",
        icon = Icons.Default.Book
    )

    object Profile : BottomBarScreen(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )
}
