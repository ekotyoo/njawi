package com.ekotyoo.njawi.presentation.belajar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.presentation.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun belajarChapter(isi: String, judul: String) {
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
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.id_sinau_njawi),
            contentDescription = "icon sinau",
            Modifier.size(width = 254.dp, height = 122.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            count = 3, modifier = Modifier.fillMaxHeight(0.6f)
        ) {
            box(
                isi = isi,
                judul = judul,
                style = Typography.body1,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(1.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fall_inu2),
                contentDescription = "njawi mascot",
            )
            Image(
                painter = painterResource(id = R.drawable.bubble_chat),
                contentDescription = "bubble",
                modifier = Modifier.size(75.dp)
            )
        }
    }

}

@ExperimentalPagerApi
@Preview
@Composable
fun chapterPreview(){
    NjawiTheme {
        belajarChapter(isi = "dskujfhasdfuiasdbfisdbfbasdifusdifhlaskjdfhkasjbdchasdkjfbasdfyuisdhgfuisdfosadf", judul = "Krama Inggil")
    }
}

@Composable
fun box(
    isi: String,
    style: TextStyle,
    judul: String,
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        resultOutside,
                        resultOutsideBorder
                    )
                ),
                shape = Shapes.medium
            )
            .border(2.dp, color = resultOutsideBorder, shape = Shapes.medium)
            .padding(5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            resultCenter,
                            resultCenterBorder
                        )
                    ),
                    shape = Shapes.medium
                )
                .border(3.dp, color = resultCenterBorder, shape = Shapes.medium)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = judul,
                style = Typography.h1,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = isi,
                style = style,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

