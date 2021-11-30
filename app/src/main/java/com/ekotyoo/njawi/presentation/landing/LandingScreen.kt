package com.ekotyoo.njawi.presentation.landing

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekotyoo.njawi.presentation.theme.*
import com.ekotyoo.njawi.presentation.landing.components.SkipButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
@Preview
fun LandingScreen() {
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
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

        BottomSection()
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
            .padding(12.dp),
    ) {
        Indicators(size = size, index = index)

        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }
    }
}

@Composable
fun BottomSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            SkipButton(
                modifier = Modifier.width(280.dp),
                text = "Lewati",
                onClick = {
                    Log.d("skip button", "clicked")
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
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
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
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = stringResource(item.title),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )

        Text(
            text = stringResource(item.text),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.End
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        androidx.compose.foundation.Image(painter = painterResource(item.image), contentDescription = null )

        Text(
            text = stringResource(item.description),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}