package com.ekotyoo.njawi.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.LoginScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizScreen
import com.ekotyoo.njawi.presentation.quiz.PlayQuizViewModel
import com.ekotyoo.njawi.presentation.theme.Blue
import com.ekotyoo.njawi.presentation.theme.NjawiTheme
import com.ekotyoo.njawi.presentation.theme.Red
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NjawiTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }

    }
}

