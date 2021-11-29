package com.ekotyoo.njawi.presentation

const val ROOT_GRAPH_ROUTE = "root"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String) {

    object  Auth: Screen(route = "login")

    object Home: Screen(route = "home")

    object Quiz: Screen(route = "quiz")

    object LevelQuiz: Screen(route = "level_quiz")

    object PlayQuiz: Screen(route = "play_quiz")

    object Belajar: Screen(route = "belajar")

    object Profile: Screen(route = "profile")
}
