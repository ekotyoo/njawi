package com.ekotyoo.njawi.common.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String) {

    object  Auth: Screen(route = "login")

    object Home: Screen(route = "home")

    object Quiz: Screen(route = "quiz")

    object LevelQuiz: Screen(route = "level_quiz")

    object PlayQuiz: Screen(route = "play_quiz/{id}")

    object Belajar: Screen(route = "belajar")

    object BelajarDetail: Screen(route = "belajar/{id}")

    object Profile: Screen(route = "profile")
}
