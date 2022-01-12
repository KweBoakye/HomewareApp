package com.kweboakye.homewareapp.ui.mainpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.kweboakye.homewareapp.ui.mainGrey

@Composable
fun BottomBar(
        modifier: Modifier = Modifier,
    fabShape: Shape,
    previous: () -> Unit,
    next: () -> Unit,
    currentPage: Int,
    totalPagesCount: Int
){
    BottomAppBar(
        cutoutShape = fabShape,
    elevation = 8.dp,
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(36.dp))
        IconButton(
            modifier = Modifier.scale(1.5f),
            onClick = previous,

        ) {
            Surface(
                color = mainGrey,
                shape = CircleShape
            ) {
                Icon(
                    imageVector =Icons.Filled.ChevronLeft,
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Column() {
            Spacer(modifier = Modifier.height((80/3).dp))
            Row() {
                Text(text = "$currentPage ")
                Text(text = "/")
                Text(text = "$totalPagesCount")
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))
        IconButton(
            modifier = Modifier.scale(1.5f),
            onClick = next,
           ) {
            Surface(
                color = mainGrey,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.width(36.dp))



    }
}

@Preview
@Composable
fun BottomBarPreview(){
    Scaffold(
        bodyContent = {},
        bottomBar = { BottomBar(fabShape = RoundedCornerShape(50.dp),
        previous = {},
        next = {},
                currentPage = 1, totalPagesCount = 4) }
    )

}