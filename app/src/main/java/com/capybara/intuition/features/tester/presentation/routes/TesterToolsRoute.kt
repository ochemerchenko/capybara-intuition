package com.capybara.intuition.features.tester.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.app.App
import com.capybara.intuition.features.tester.components.generator.createHistoricalDatasetManager
import com.capybara.intuition.features.tester.presentation.ui.TesterToolsScreen


@Composable
fun TesterToolsRoute(
    modifier: Modifier = Modifier
) {
    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer

    // This route is only accessible in TEST environment, 
    // where container.tester is guaranteed to be non-null.
    val tester = requireNotNull(container.tester) {
        "TesterModule is required for TesterToolsRoute"
    }

    val historicalDatasetManager = remember {
        createHistoricalDatasetManager(
            historicalDatasetRepository = tester.historicalDatasetRepository
        )
    }

    TesterToolsScreen(
        historicalDatasetManager = historicalDatasetManager,
        modifier = modifier
    )
}
