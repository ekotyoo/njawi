package com.ekotyoo.njawi.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.model.AuthViewModel
import com.ekotyoo.njawi.presentation.profile.components.Circle

@Composable
fun LoginView(onClick: () -> Unit, authViewModel: AuthViewModel) {
    Scaffold {
        Box {
            Circle()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(19.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Njawi",
                    fontSize = 72.sp,
                    fontFamily = pacifico,
                    color = Color(0xFFFFAE02),
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.inu5),
                    contentDescription = "App_icon",
                    Modifier.size(400.dp)
                )
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
//            errorText?.let {
//                Spacer(modifier = Modifier.height(3.dp))
//                Text(text = it)
//            }
            }
        }
    }
}