package com.kweboakye.homewareapp

import android.os.Build
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.kweboakye.homewareapp.ui.HomewareAppTheme
import com.kweboakye.homewareapp.ui.mainpage.BottomBar
import com.kweboakye.homewareapp.ui.mainpage.MainFloatingActionButton
import com.kweboakye.homewareapp.ui.mainpage.MainPage
import com.kweboakye.homewareapp.ui.mainpage.MainPager
import com.kweboakye.homewareapp.ui.util.PagerState
import dev.chrisbanes.accompanist.insets.*

class MainActivity : AppCompatActivity() {

    private var insets = mutableStateOf(WindowInsetsCompat.Builder().build())
    private val systemWindowInsetListener = { view: View, insets: WindowInsetsCompat ->
        this.insets.value = insets
        insets}

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView, systemWindowInsetListener)

        setContent {
            HomewareAppTheme {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.statusBarColor = Color.Transparent.toArgb()
                MainPage()
            }
        }
    }
}

@Composable
fun InsetAwareTopAppBar(
        title: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        navigationIcon: @Composable (() -> Unit)? = null,
        actions: @Composable RowScope.() -> Unit = {},
        backgroundColor: Color = MaterialTheme.colors.primarySurface,
        contentColor: Color = contentColorFor(backgroundColor),
        elevation: Dp = 4.dp
) {
    Surface(
        color = backgroundColor,
        elevation = elevation,
        modifier = modifier
    ) {
        TopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            backgroundColor = Color.Transparent,
            contentColor = contentColor,
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding()
        )
    }
}

