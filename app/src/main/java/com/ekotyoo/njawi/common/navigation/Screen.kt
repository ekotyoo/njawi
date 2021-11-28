package com.ekotyoo.njawi.common.navigation

const val HOME_ROUTE = "homewrapper/{user}"
const val AUTH_ROUTE = "authwrapper"

sealed class Screen(val route: String){
    object Auth: Screen(route = "login")
    object Quiz: Screen(route = "quiz")
    object Home: Screen(route = "home")
    object Belajar: Screen(route = "belajar")
    object Profile: Screen(route = "profile")
}
