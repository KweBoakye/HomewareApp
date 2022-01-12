package com.kweboakye.homewareapp.ui.mainpage

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainFloatingActionButton(
        modifier: Modifier = Modifier,
        onClick: () -> Unit
) {
    FloatingActionButton(
            modifier = modifier,
            onClick = onClick,
            shape = CircleShape) {
            Icon(imageVector = Icons.Outlined.ShoppingCart)
    }
}