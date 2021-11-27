package com.ekotyoo.njawi.presentation.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.model.AuthViewModel
import com.ekotyoo.njawi.presentation.auth.utils.AuthResultContract
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

val pacifico = FontFamily(Font(R.font.pacifico_regular))

@Composable
fun LoginView(onClick: () -> Unit, errorText: String?) {
    Scaffold {
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
                .padding(19.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Njawi", fontSize = 72.sp, fontFamily = pacifico, color = Color(0xFFFFAE02), fontWeight = FontWeight.Bold)
            Image(painter = painterResource(id = R.drawable.inu_1), contentDescription = "App_icon", Modifier.size(500.dp))
            GoogleButton(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(24.dp),
                text = "Login with Google",
                loadingText = "Login Account...",
                onClicked = {
                    onClick()
                    Log.d("Login Google button", "clicked")
                }
            )
            GoogleButton(
                modifier = Modifier.width(280.dp),
                shape = RoundedCornerShape(24.dp),
                onClicked = {
                    onClick()
                    Log.d("Sign Up Google button", "clicked")
                }
            )
            errorText?.let {
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = it)
            }
        }
    }
}

@Composable
fun LoginScreen(authViewModel: AuthViewModel){

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null)}
    val user by remember(authViewModel){authViewModel.user}.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()){ task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account==null){
                    text = "Google sign in failed"
                }else{
                    coroutineScope.launch {
                        account.email?.let { account.displayName?.let { it1 -> authViewModel.signIn(email = it, displayName = it1) } }
                    }
                }
            }catch (e:ApiException){
                text="Google sign in failed"
            }
        }
    LoginView(errorText = text,onClick = {text=null
        authResultLauncher.launch(signInRequestCode)
    })
    user?.let{
        HomeScreen(user = it)
    }
}