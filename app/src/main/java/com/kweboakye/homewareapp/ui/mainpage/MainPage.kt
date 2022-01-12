package com.kweboakye.homewareapp.ui.mainpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AndroidDialogProperties
import androidx.compose.ui.window.Dialog
import com.kweboakye.homewareapp.R
import com.kweboakye.homewareapp.ui.HomewareAppTheme
import com.kweboakye.homewareapp.ui.util.PagerState
import dev.chrisbanes.accompanist.insets.AmbientWindowInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@ExperimentalMaterialApi
@Composable
fun MainPage(){

    val clock = AmbientAnimationClock.current
    val pagerState = remember(clock) { PagerState(clock) }
    val (openDialog, setOpenDialog) = remember { mutableStateOf(false)  }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)




    pagerState.maxPage = (photoList.size - 1).coerceAtLeast(0)
    ProvideWindowInsets() {
        val top = AmbientWindowInsets.current.statusBars.top
        Surface(color = Color.Blue) {
            ModalBottomSheetLayout(
                    sheetContent = { AddToBasketDialog() },
                    sheetState = bottomSheetState
            ) {
            Scaffold(
                    modifier = Modifier.navigationBarsPadding().fillMaxHeight(),
                    bodyContent = { innerPadding ->
                        MainPager(
                                modifier = Modifier.padding(innerPadding),
                                pagerState = pagerState,
                                photoList = photoList
                        )
                    },
                    floatingActionButton = {
                        if (!openDialog) {
                            MainFloatingActionButton(
                                    modifier = Modifier.offset(y = (-8).dp),
                                    onClick = {
                                        bottomSheetState.show()
                                    }
                            )
                        }
                    },
                    bottomBar = {
                            BottomBar(
                                    fabShape = CircleShape,
                                    previous = { pagerState.fling(12.5f) },
                                    next = { pagerState.fling(-12.5f) },
                                    currentPage = pagerState.currentPage + 1,
                                    totalPagesCount = photoList.size
                            )
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
            )
        }
    }
    }
}

val photoList = listOf(
        R.drawable.white_jug,
        R.drawable.jug,
        R.drawable.ornate_teapot,
        R.drawable.red_teapot
)

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomewareAppTheme {
        MainPage()
    }
}

@Composable
fun AddToBasketDialog(
) {

            Column(
                    Modifier.height(300.dp)
                    .padding(horizontal = 60.dp)
            ) {
                Row() {
                    Spacer(modifier = Modifier.fillMaxWidth())
                    IconButton(
                            onClick = { /*TODO*/ }) {
                        Icon(
                                modifier = Modifier.size(64.dp),
                                imageVector = Icons.Filled.Close)
                    }
                }
                Spacer(modifier = Modifier.width(60.dp))
                Row() {
                    val image = loadImageResource(id = R.drawable.jug)
                    image.resource.resource?.let {
                        Image(
                                bitmap = it,
                                modifier = Modifier.width(80.dp))
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column() {
                        Text(text = " Stoneware Vase",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                                )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                                text = "Handrowan llllllll ubububhb wcwcwdcw, ecedcccdcs, asccascsa wcacsdcascc wcdcsacsdc oijoijojc",
                                maxLines = 2,
                                fontSize = 8.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                                text = "£34.99",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row() {
                            Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.size(width = 80.dp, height = 24.dp)
                            ) {
                                Text(text = "Grey", fontSize = 12.sp, fontWeight = FontWeight.Normal)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Brown")
                            }
                        }
                    }

                }
                Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                .fillMaxWidth()
                        ) {
                    Text(text = "AddTo Cart")
                }
            }
}