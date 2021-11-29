package com.ekotyoo.njawi.presentation.belajar

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ekotyoo.njawi.R
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.presentation.Screen
import com.ekotyoo.njawi.presentation.belajar.components.ItemView
import com.ekotyoo.njawi.presentation.profile.PhotographerCard
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.quiz.HeaderAnimation


@ExperimentalFoundationApi
@Composable
fun BelajarScreen(
    viewModel: BelajarViewModel = hiltViewModel(),
    navController: NavHostController
){
    val runImages: List<Int> = listOf(R.drawable.lari1, R.drawable.lari2, R.drawable.lari3, R.drawable.lari4, R.drawable.lari5, R.drawable.lari6, R.drawable.lari7, R.drawable.lari8)
    Box {
        Circle()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.id_sinau_njawi),
                contentDescription = "icon sinau",
                Modifier.size(width = 254.dp, height = 122.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            when(val materisResponse = viewModel.materisState.value) {
                is Response.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    HeaderAnimation(runImages)
                }

                is Response.Success -> LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ){
                    items(
                        materisResponse.data.size
                    ){
                        materisResponse.data[it].title?.let { it1 ->
                            ItemView(
                                it1,
                                modifier = Modifier
                                    .size(width = 134.dp, height = 134.dp)
                                    .clickable {
                                        navController.navigate(
                                            Screen.BelajarDetail.route.replace("{id}",
                                                materisResponse.data[it].id!!
                                            )
                                        )

                                    },
                                textColor = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}