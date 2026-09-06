package com.capybara.intuition.core.layout

import androidx.compose.runtime.compositionLocalOf

val LocalAppLayoutEnvironment =
    compositionLocalOf<AppLayoutEnvironment> {

        error(
            "No AppLayoutEnvironment provided"
        )
    }