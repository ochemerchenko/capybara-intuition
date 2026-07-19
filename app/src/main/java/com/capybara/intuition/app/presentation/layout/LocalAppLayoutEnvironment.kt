package com.capybara.intuition.app.presentation.layout

import androidx.compose.runtime.compositionLocalOf

val LocalAppLayoutEnvironment =
    compositionLocalOf<AppLayoutEnvironment> {

        error(
            "No AppLayoutEnvironment provided"
        )
    }