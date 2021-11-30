package com.ekotyoo.njawi.presentation.landing

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ekotyoo.njawi.presentation.MainActivity
import com.ekotyoo.njawi.presentation.landing.components.SkipButton
import com.ekotyoo.njawi.presentation.profile.components.Circle
import com.ekotyoo.njawi.presentation.theme.White
import com.ekotyoo.njawi.presentation.theme.blueIce
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun LandingScreen(
    navController: NavController
) {
    val context = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    Scaffold {
        Box(
            modifier = Modifier.pointerInput(Unit){}
        ) {
            Circle()
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 19.dp)
                    .pointerInput(Unit) {}) {
                val items = OnBoardingItem.get()
                val state = rememberPagerState(initialPage = items.size)

                HorizontalPager(
                    count = 2,
                    state = state,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.8f),
                ) {page->
                    OnBoardingItem(items[page])
                }

                TopSection(size = items.size, index = state.currentPage) {
                    if((state.currentPage + 1) < items.size) {
                        scope.launch {
                            state.scrollToPage(state.currentPage + 1)
                        }
                    }
                }

                BottomSection(context)
            }
        }
    }
}

@Composable
fun TopSection(
    size: Int,
    index: Int,
    onNextClicked:()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .pointerInput(Unit) {},
    ) {
        Indicators(size = size, index = index)
    }
}

@Composable
fun BottomSection(
    context: Context
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {}
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            SkipButton(
                modifier = Modifier.fillMaxWidth(0.7f),
                shape = RoundedCornerShape(24.dp),
                text = "Lewati",
                onClicked = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun BoxScope.Indicators(size:Int,index:Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center)
    ) {
        repeat(size){
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if(isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) blueIce
                else White.copy(alpha = 0.5f)
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(
    item: OnBoardingItem
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Text(text = stringResource(item.title),
            fontSize = 24.sp,
            color = White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )

        Text(
            fontSize = 20.sp,
            text = stringResource(item.text),
            color = White,
            textAlign = TextAlign.End
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        androidx.compose.foundation.Image(painter = painterResource(item.image), contentDescription = null )

        Text(
            text = stringResource(item.description),
            color = White,
            textAlign = TextAlign.Center
        )
    }
}