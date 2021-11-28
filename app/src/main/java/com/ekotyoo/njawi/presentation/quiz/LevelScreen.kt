package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.components.LevelButton
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

@Composable
fun LevelScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(R.drawable.sinau),
            contentDescription = "foto",
        modifier = Modifier.padding(top = 50.dp, bottom = 50.dp))
        LevelButton(1)
        LevelButton(2)
        LevelButton(3)
        LevelButton(4)
        LevelButton(5)
        LevelButton(6)
    }
}



@Preview
@Composable
fun HalamanLevel(){
    NjawiTheme {
        Box {
            Circle()
            LevelScreen()
        }
    }
}