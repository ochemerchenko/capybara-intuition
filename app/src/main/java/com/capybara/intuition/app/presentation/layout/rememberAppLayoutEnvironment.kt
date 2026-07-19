package com.capybara.intuition.app.presentation.layout

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


@Composable
fun rememberAppLayoutEnvironment(): AppLayoutEnvironment {

    val configuration = LocalConfiguration.current

    return remember(configuration) {
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp
        AppLayoutEnvironment(
            screenWidth = screenWidth,
            screenHeight = screenHeight,
            contentWidth = screenWidth,
            screenContentPadding = 16.dp,
            screenSectionSpacing = 16.dp,
            contentHeight = screenHeight,
            isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
        )
    }
}