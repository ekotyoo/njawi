package com.ekotyoo.njawi.presentation

//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.ekotyoo.njawi.R
//import com.ekotyoo.njawi.presentation.auth.LoginScreen
//import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
//import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
//
//@Composable
//fun SetupNavGraph(
//    navController: NavHostController
//){
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Login.route
//    ) {
//       composable(
//           route = Screen.Login.route
//       ) {
//           LoginScreen
//       }
//        composable(
//            route = Screen.Quiz.route
//        ){
//            PlayQuizScreen(headerImg = R.drawable.img_susun_kalimat, PlayQuizViewModel())
//        }
//    }
//}