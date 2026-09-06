package com.capybara.intuition.features.game.presentation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.capybara.intuition.core.layout.LocalAppLayoutEnvironment


@Composable
fun rememberGameLayoutMetrics(
    rowsCount: Int,
    maxColumns: Int
): GameLayoutMetrics {

    val environment =
        LocalAppLayoutEnvironment.current

    return remember(
        environment,
        rowsCount,
        maxColumns
    ) {

        val contentWidth =
            environment.contentWidth

        val contentHeight =
            environment.contentHeight

        // --- reserved zones ---

        val bottomPanelHeight =
            //contentHeight * 0.22f
            contentHeight * 0.40f

        val availableGridHeight =
            contentHeight - bottomPanelHeight

        // --- adaptive spacing ---

        val horizontalSpacingFactor =
            //0.12f / (maxColumns + 1)
            0.12f / (maxColumns + 1)

        val verticalSpacingFactor =
            //0.10f / (rowsCount + 1)
            0.10f / (rowsCount + 1)

        val horizontalSpacing =
            contentWidth * horizontalSpacingFactor

        val verticalSpacing =
            availableGridHeight * verticalSpacingFactor

        // --- total spacing ---

        val totalHorizontalSpacing =
            horizontalSpacing * (maxColumns + 1)

        val totalVerticalSpacing =
            verticalSpacing * (rowsCount + 1)

        // --- calculate button size ---

        val widthBasedButtonSize =
            (contentWidth - totalHorizontalSpacing) /
                    maxColumns

        val heightBasedButtonSize =
            (availableGridHeight - totalVerticalSpacing) /
                    rowsCount

        val buttonSize =
            minOf(
                widthBasedButtonSize,
                heightBasedButtonSize
            )

        // --- secondary metrics ---

        val capybaraSize =
            buttonSize * 1.8f

        val indicatorSize =
            buttonSize * 0.42f

        GameLayoutMetrics(

            buttonSize = buttonSize,

            horizontalSpacing = horizontalSpacing,

            verticalSpacing = verticalSpacing,

            gridBottomPadding = verticalSpacing,

            capybaraSize = capybaraSize,

            indicatorSize = indicatorSize
        )
    }
}