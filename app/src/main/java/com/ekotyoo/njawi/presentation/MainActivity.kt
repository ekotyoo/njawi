package com.ekotyoo.njawi.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.presentation.theme.Blue
import com.ekotyoo.njawi.presentation.theme.Red
import kotlinx.coroutines.delay
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.navigation.NavHostContainer
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    private lateinit var mediaPlayer: MediaPlayer

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz)
        mediaPlayer.isLooping = true
        mediaPlayer.start()


        setContent {
            NjawiTheme {
                // remember navController so it does not
                // get recreated on recomposition
                val navController = rememberNavController()
                Surface(color = Color.White) {
//                    Scaffold(
//                        bottomBar = {
//                            BottomNavigationBar(navController = navController)
//                        }, content = {
//                            NavHostContainer(navController = navController)
//                        }
//                    )
                    NavHostContainer(navController = navController)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}


