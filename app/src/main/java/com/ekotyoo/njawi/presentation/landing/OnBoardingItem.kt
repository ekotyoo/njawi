package com.ekotyoo.njawi.presentation.landing

import com.ekotyoo.njawi.R

class OnBoardingItem(
    val title:Int,
    val text:Int,
    val description:Int,
    val image:Int,
) {
        companion object{
            fun get():List<OnBoardingItem> {
                return listOf(
                    OnBoardingItem(R.string.onBoardingTitle1, R.string.onBoardingText1, R.string.onBoardingDescription1, R.drawable.lari3),
                    OnBoardingItem(R.string.onBoardingTitle2, R.string.onBoardingText2, R.string.onBoardingDescription2, R.drawable.lari7),
                )
            }
        }
}