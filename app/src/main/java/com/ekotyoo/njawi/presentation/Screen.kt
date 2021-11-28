package com.ekotyoo.njawi.presentation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String){
    object Auth: Screen(route = "login")
    object Quiz: Screen(route = "quiz")
    object Home: Screen(route = "home/{user}")
    object Profile: Screen(route = "profile")
    object Belajar: Screen(route = "belajar")
}
