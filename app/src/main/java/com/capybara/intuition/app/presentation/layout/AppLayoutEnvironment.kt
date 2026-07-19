package com.capybara.intuition.app.presentation.layout

import androidx.compose.ui.unit.Dp

data class AppLayoutEnvironment(
    val screenWidth: Dp,
    val screenHeight: Dp,

    val contentWidth: Dp,
    val contentHeight: Dp,


    val screenContentPadding: Dp,
    val screenSectionSpacing: Dp,

    val isLandscape: Boolean,
)