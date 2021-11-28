package com.ekotyoo.njawi.presentation.auth

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ekotyoo.njawi.HomeActivity
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.model.AuthViewModel
import com.ekotyoo.njawi.presentation.auth.model.AuthViewModelFactory
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.auth.utils.GoogleApiContract
import com.google.android.gms.common.api.ApiException
import com.squareup.moshi.Moshi

val pacifico = FontFamily(Font(R.font.pacifico_regular))

@Composable
fun LoginView(onClick: () -> Unit, authViewModel: AuthViewModel) {
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Njawi", fontSize = 72.sp, fontFamily = pacifico, color = Color(0xFFFFAE02), fontWeight = FontWeight.Bold)
            Image(painter = painterResource(id = R.drawable.inu_1), contentDescription = "App_icon", Modifier.size(400.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Spacer(modifier = Modifier.height(19.dp))
                GoogleButton(
                    modifier = Modifier.width(280.dp),
                    shape = RoundedCornerShape(24.dp),
                    onClicked = {
                        onClick()
                        Log.d("Sign Up Google button", "clicked")
                    }
                )
            }
//            errorText?.let {
//                Spacer(modifier = Modifier.height(3.dp))
//                Text(text = it)
//            }
        }
    }
}

@Composable
fun AuthScreen(navController: NavController) {
    val signInRequestCode = 1
    val context = LocalContext.current

    val mSignInViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(context.applicationContext as Application)
    )

    val state = mSignInViewModel.googleUser.observeAsState()
    val user = state.value

    val isError = rememberSaveable { mutableStateOf(false) }

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)

                if (gsa != null) {
                    mSignInViewModel.fetchSignInUser(gsa.email, gsa.displayName)
                    Log.d("Username", "Gsa Not Null")
                } else {
                    isError.value = true
                    Log.d("Username", "error")
                }
            } catch (e: ApiException) {
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }

    LoginView(
        onClick = { authResultLauncher.launch(signInRequestCode) },
        mSignInViewModel
    )

    user?.let {
        mSignInViewModel.hideLoading()
        val username = it.name
        val email = it.email

        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra("name", username)
        intent.putExtra("email", email)

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(User::class.java).lenient()
        val userJson = jsonAdapter.toJson(user)

        val context = LocalContext.current
        context.startActivity(intent)
    }
}