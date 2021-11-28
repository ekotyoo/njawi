package com.ekotyoo.njawi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.presentation.HomeScreen
import com.ekotyoo.njawi.presentation.auth.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var user: User
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras
        if (extras != null) {
            val username = extras.getString("name")
            val email = extras.getString("email")
            user = User(username, email)
        }
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(navController = rememberNavController(), user = user)
        }
    }
}