package com.ekotyoo.njawi

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.common.navigation.NavHostContainer
import com.ekotyoo.njawi.presentation.auth.model.User
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var user: User

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.start()
    }

    @InternalCoroutinesApi
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        val extras = intent.extras
        if (extras != null) {
            val username = extras.getString("name")
            val email = extras.getString("email")
            user = User(username, email)
        }
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContent {
            NavHostContainer(navController = rememberNavController(), user = user)
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}