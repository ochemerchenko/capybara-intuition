package com.capybara.intuition.features.profile.tester.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.app.App
import com.capybara.intuition.features.profile.tester.components.generator.createHistoricalDatasetManager
import com.capybara.intuition.features.profile.tester.presentation.ui.TesterToolsScreen


@Composable
fun TesterToolsRoute(
    modifier: Modifier = Modifier
) {
    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer

    val historicalDatasetManager = remember {
        createHistoricalDatasetManager(
            historicalDatasetRepository =
                container.tester.historicalDatasetRepository
        )
    }

    TesterToolsScreen(
        historicalDatasetManager = historicalDatasetManager,
        modifier = modifier
    )
}