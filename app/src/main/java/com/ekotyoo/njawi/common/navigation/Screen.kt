package com.ekotyoo.njawi.common.navigation


sealed class Screen(val route: String){
    object Auth: Screen(route = "login")
    object Quiz: Screen(route = "quiz")
    object Home: Screen(route = "home/{user}")
    object Belajar: Screen(route = "belajar")
    object Profile: Screen(route = "profile")
}
