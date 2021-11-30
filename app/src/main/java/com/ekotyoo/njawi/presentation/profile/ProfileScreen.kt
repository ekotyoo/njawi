package com.ekotyoo.njawi.presentation.profile

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.auth.model.User
import com.ekotyoo.njawi.presentation.auth.pacifico
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.profile.components.Expandbox



@Composable
fun PhotographerCard(
    modifier: Modifier = Modifier,
    title: String,
    user: User,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getAchievements()
    }

    val items by viewModel.achievements.collectAsState()

    Box {
        Circle()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier
                .height(30.dp)
                .fillMaxWidth())
            Text(text = "Njawi", fontSize = 30.sp,
                fontFamily = pacifico, color = Color(0xFFFFAE02),
                fontWeight = FontWeight.Normal)
            Row(
                modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(R.drawable.chad),
                    contentDescription = "foto",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(
                                3.dp, brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFFFDE02),
                                        Color(0xFFFF9F00),
                                        Color(0xFFFFAE02),
                                    ),
                                )
                            ), shape = CircleShape
                        ),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    user.name?.let { Text(it, fontWeight = FontWeight.Medium, color = Color.White, fontSize = 20.sp) }
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        user.email?.let { Text(it,fontWeight = FontWeight.Light, color = Color.White, fontSize = 15.sp) }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Expandbox(title = "Achievement", items = items)
            Spacer(modifier = Modifier.height(150.dp))

        }
    }
}
