package com.ekotyoo.njawi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.presentation.auth.LoginScreen
import com.ekotyoo.njawi.presentation.landing.LandingScreen
import com.ekotyoo.njawi.presentation.theme.NjawiTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NjawiTheme {
//                LoginScreen(rememberNavController())
                LandingScreen()
            }
        }
    }

}


