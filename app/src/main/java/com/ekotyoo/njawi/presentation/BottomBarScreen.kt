package com.ekotyoo.njawi.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Quiz: BottomBarScreen(
        route = "quiz",
        title = "Quiz",
        icon = Icons.Default.Home
    )
    object InfoNjawi: BottomBarScreen(
        route = "infoNjawi",
        title = "InfoNjawi",
        icon = Icons.Default.Email
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
