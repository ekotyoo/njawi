package com.ekotyoo.njawi.presentation

sealed class Screen(val route: String){
    object Auth: Screen(route = "login")
    object Quiz: Screen(route = "quiz")
    object Home: Screen(route = "home/{user}")
}
