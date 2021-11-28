package com.ekotyoo.njawi.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.R

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        button = TextStyle(
                fontFamily = FontFamily(Font(R.font.luckiest_guy, FontWeight.W400)),
                fontSize = 18.sp,
        ),
        body2 = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)