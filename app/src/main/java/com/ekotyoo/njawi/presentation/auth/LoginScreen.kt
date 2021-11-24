package com.ekotyoo.njawi.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),
                        Color(0xFF1268C3)
                    ),
                )
            )
            .padding(top = 19.dp, bottom = 29.dp, start = 19.dp, end = 19.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Njawi", fontSize = 72.sp, fontFamily = pacifico, color = Color(0xFFFFAE02), fontWeight = FontWeight.Bold)
        Image(painter = painterResource(id = R.drawable.inu_1), contentDescription = "Anjing", Modifier.size(300.dp))
        GoogleButton(
            modifier = Modifier.width(280.dp),
            shape = RoundedCornerShape(24.dp),
            text = "Login with Google",
            loadingText = "Login Account...",
            onClicked = {
                navController.navigate(route = Screen.Quiz.route)
                Log.d("Login Google button", "clicked")
            }
        )
        GoogleButton(
            modifier = Modifier.width(280.dp),
            shape = RoundedCornerShape(24.dp),
            onClicked = {
                navController.navigate(route = Screen.Quiz.route)
                Log.d("Sign Up Google button", "clicked")
            }
        )
    }
}

val pacifico = FontFamily(Font(R.font.pacifico_regular))

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NjawiTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFD24074),
                            Color(0xFF1268C3)
                        ),
                    )
                )
                .padding(top = 19.dp, bottom = 29.dp, start = 19.dp, end = 19.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Njawi", fontSize = 72.sp, fontFamily = pacifico, color = Color(0xFFFFAE02), fontWeight = FontWeight.Bold)
            Image(painter = painterResource(id = R.drawable.inu_1), contentDescription = "App_icon", Modifier.size(height = 500.dp, width = 500.dp))
            GoogleButton(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(24.dp),
                text = "Login with Google",
                loadingText = "Login Account..."
            ) { Log.d("Login Google", "clicked") }
            GoogleButton(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(24.dp)
            ) { Log.d("Google button", "clicked") }
        }
    }
}