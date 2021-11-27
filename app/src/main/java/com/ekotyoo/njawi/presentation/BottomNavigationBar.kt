package com.ekotyoo.njawi.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.Black
import com.ekotyoo.njawi.presentation.theme.LightOrange
import com.ekotyoo.njawi.presentation.theme.Orange
import com.ekotyoo.njawi.presentation.theme.Shapes

@Composable
fun BottomNavigationBar() {
    Column() {
        Surface(
            color = Color(0xFFFFAE02),
            border = BorderStroke(
                10.dp, brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFDE02),
                        Color(0xFFCA7E00),
                    ),
                )
            ),
            modifier = Modifier
                .width(300.dp)
                .height(65.dp)
                .border(
                    BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(40)
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(40),

                ),
            shape = RoundedCornerShape(40),
        ) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_game),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(45.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_learning),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(35.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(35.dp)
        )
    }
}}}

@SuppressLint("ComposableNaming")
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}