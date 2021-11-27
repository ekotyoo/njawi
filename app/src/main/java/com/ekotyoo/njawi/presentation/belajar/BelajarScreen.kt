package com.ekotyoo.njawi.presentation.belajar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.belajar.components.ItemView

@ExperimentalFoundationApi
@Composable
fun BelajarScreen(){
    val number = (1..20).toList()
    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD24074),
                        Color(0xFF1268C3)
                    ),
                )
            )
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.id_sinau_njawi),
            contentDescription = "icon sinau",
            Modifier.size(width = 254.dp, height = 122.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ){
            items(number.size){
                ItemView(it.toString(),
                    modifier = Modifier.size(width = 134.dp, height = 134.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun test(){
    BelajarScreen()
}