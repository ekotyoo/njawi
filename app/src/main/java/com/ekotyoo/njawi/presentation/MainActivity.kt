package com.ekotyoo.njawi.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.presentation.auth.LoginScreen
import com.ekotyoo.njawi.presentation.landing.LandingScreen
import com.ekotyoo.njawi.presentation.theme.NjawiTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import android.content.SharedPreferences
import com.ekotyoo.njawi.BuildConfig

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val PREFS_NAME = "com.ekotyoo.njawi"
        val PREF_VERSION_CODE_KEY = "1.0"
        val DOESNT_EXIST = -1

        // Get current version code
        val currentVersionCode: Int = BuildConfig.VERSION_CODE

        // Get saved version code
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            setContent {
                NjawiTheme {
                    LoginScreen(rememberNavController())
                }
            }
        } else if (savedVersionCode == DOESNT_EXIST) {
            setContent {
                NjawiTheme {
                    LandingScreen()
                }
            }
        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply()
    }
}


