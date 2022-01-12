package com.kweboakye.homewareapp.ui.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredSizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp

@Composable
fun LocalResourceImageComponent(@DrawableRes resId: Int) {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the loadImageResource method as it loads an image resource asynchronously
    val image = loadImageResource(resId)
    image.resource.resource?.let {
        // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. In this example, we configure the
        // Image composable to have a height of 200 dp.
        Image(
            bitmap = it,
            modifier = Modifier.preferredSizeIn(maxHeight = 200.dp)
                .fillMaxWidth())
    }
}