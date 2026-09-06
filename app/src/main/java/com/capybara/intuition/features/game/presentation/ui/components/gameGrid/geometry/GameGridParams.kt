package com.capybara.intuition.features.game.presentation.ui.components.gameGrid.geometry

import androidx.compose.ui.unit.Dp

data class GameGridParams(
    val buttonDiameter: Dp,
    val buttonRadius: Dp,
    val margin: Dp,
    val cells: List<CellGeometry>
)

