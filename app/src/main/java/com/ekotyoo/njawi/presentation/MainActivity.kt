package com.ekotyoo.njawi.presentation

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.R
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
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}

