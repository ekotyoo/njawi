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
import com.ekotyoo.njawi.common.Constants.DOESNT_EXIST
import com.ekotyoo.njawi.common.Constants.PREFS_NAME
import com.ekotyoo.njawi.common.Constants.PREF_VERSION_CODE_KEY
import com.firebase.ui.auth.BuildConfig

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentVersionCode: Int = BuildConfig.VERSION_CODE

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        setContent {
            NjawiTheme {
                if (currentVersionCode == savedVersionCode) {
                    LoginScreen(rememberNavController())
                } else if (savedVersionCode == DOESNT_EXIST) {
                    LandingScreen(rememberNavController())
                }

            }
        }

        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply()
    }
}


