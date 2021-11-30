package com.ekotyoo.njawi.presentation.auth

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ekotyoo.njawi.presentation.auth.utils.GoogleApiContract
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.google.android.gms.common.api.ApiException

val pacifico = FontFamily(Font(R.font.pacifico_regular))

@Composable
fun LoginScreen(navController: NavController) {
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
                    mSignInViewModel.fetchSignInUser(gsa.email, gsa.displayName, gsa.photoUrl?.toString() ?: "https://i.pinimg.com/474x/65/25/a0/6525a08f1df98a2e3a545fe2ace4be47.jpg")
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
        val image = it.image

        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra("name", username)
        intent.putExtra("email", email)
        intent.putExtra("image", image)

        val context = LocalContext.current as Activity
        context.startActivity(intent)
        context.finish()
    }
}