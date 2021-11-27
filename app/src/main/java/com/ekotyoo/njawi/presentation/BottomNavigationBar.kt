package com.ekotyoo.njawi.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Orange, shape = Shapes.medium)
            .border(3.dp, Black, Shapes.medium)
            .padding(3.dp)
            .border(8.dp, LightOrange, Shapes.medium),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_game),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_learning),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(46.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(46.dp)
        )
    }
}

@SuppressLint("ComposableNaming")
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}