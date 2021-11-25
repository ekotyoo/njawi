package com.ekotyoo.njawi.presentation

sealed class Screen(val route: String){
    object Login: Screen(route = "login_screen")
    object Quiz: Screen(route = "quiz_screen")
}
