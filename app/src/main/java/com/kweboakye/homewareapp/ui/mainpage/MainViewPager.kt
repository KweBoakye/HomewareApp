package com.kweboakye.homewareapp.ui.mainpage

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.FiberManualRecord
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kweboakye.homewareapp.ui.util.Pager
import com.kweboakye.homewareapp.ui.util.PagerState
import dev.chrisbanes.accompanist.insets.AmbientWindowInsets
import dev.chrisbanes.accompanist.insets.add
import dev.chrisbanes.accompanist.insets.toPaddingValues
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun MainPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState = run {
        val clock = AmbientAnimationClock.current
        remember(clock) { PagerState(clock) }
    },
    photoList: List<Int>
){
    val topAppBarSize = remember { mutableStateOf(0) }
    val bottomAppBarSize = remember { mutableStateOf(0) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (pager, pageIndicator) = createRefs()
        Pager(
            state = pagerState,
            modifier = Modifier.padding(
                    AmbientWindowInsets.current.systemBars
                            .toPaddingValues(top = false, bottom = false)
                            .add(top = with(AmbientDensity.current) { topAppBarSize.value.dp} )
                            .add(bottom = with(AmbientDensity.current) { bottomAppBarSize.value.dp })
            )
                .constrainAs(pager){
                    top.linkTo(parent.top)
                }) {
            val image = loadImageResource( photoList[page])
            image.resource.resource?.let {
                Image(
                    bitmap = it,
                    modifier = Modifier.fillMaxHeight().clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillHeight)
            }
        }

       PageIndicator(
           pagesCount = photoList.size,
           currentPageIndex = pagerState.currentPage,
           modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 120.dp)
               .constrainAs(pageIndicator){
                   bottom.linkTo(parent.bottom)
       })
    }
}

@Composable
private fun PageIndicator(pagesCount: Int, currentPageIndex: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        for (pageIndex in 0 until pagesCount) {

            Crossfade(pageIndex, animation = tween(
                durationMillis =10,
                delayMillis = 0
            )
            ) { page ->
                val asset = when (currentPageIndex == page) {
                    true -> Icons.Filled.Remove
                    false-> Icons.Outlined.FiberManualRecord
                }
                Icon(
                    imageVector = asset,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

enum class PageIndicatorState {
    SELECTED,
    UNSELECTED
}


@Preview
@Composable
fun MainPagerPreview() {
    MainPager(
        pagerState = run {
            val clock = AmbientAnimationClock.current
            remember(clock) { PagerState(clock) }
        },
        photoList = photoList
    )
}