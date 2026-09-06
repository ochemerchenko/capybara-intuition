package com.capybara.intuition.features.game.presentation.ui.components.gameGrid.geometry

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.times
import com.capybara.intuition.features.game.domain.model.GameSession


fun calculateGameGridParams(
    session: GameSession,
    availableWidth: Dp,
    availableHeight: Dp
): GameGridParams {

    val topology =
        session.profile.activeTopology

    val topologyWidth =
        topology.spaceWidth

    val topologyHeight =
        topology.spaceHeight

    Log.d(
        "GRID_DEBUG",
        """
        =================================================
        availableWidth=$availableWidth
        availableHeight=$availableHeight

        topology=${topology.code}
        topologyWidth=$topologyWidth
        topologyHeight=$topologyHeight
        =================================================
        """.trimIndent()
    )

    val gapRatio = 0.10f

    val SAFETY_MARGIN = 1.dp

    val virtualSize =
        min(
            availableWidth / topologyWidth,
            availableHeight / topologyHeight
        ) - SAFETY_MARGIN

    val horizontalIndent =
        (
                availableWidth -
                        virtualSize * topologyWidth
                ) / 2

    val verticalIndent =
        (
                availableHeight -
                        virtualSize * topologyHeight
                ) / 2

    val margin =
        virtualSize * gapRatio

    val buttonDiameter =
        virtualSize - margin * 2

    val cellPitch =
        buttonDiameter + margin * 2

    Log.d(
        "GRID_DEBUG",
        """
        virtualSize=$virtualSize
        horizontalIndent=$horizontalIndent
        verticalIndent=$verticalIndent

        gapRatio=$gapRatio
        margin=$margin

        buttonDiameter=$buttonDiameter
        buttonRadius=${buttonDiameter / 2}
        cellPitch=$cellPitch
        """.trimIndent()
    )

    val cellParams =
        topology.cells
            .filter { !it.blocked }
            .map { cell ->

                val rowCells =
                    topology.cells
                        .filter {
                            !it.blocked &&
                                    it.y == cell.y
                        }

                val rowWidth =
                    rowCells.maxOf { it.x } + 1

                val rowOffset =
                    when (
                        topology.projection.horizontalAlignment
                    ) {
                        "CENTER" -> {
                            (
                                    topologyWidth -
                                            rowWidth
                                    ) * cellPitch / 2f
                        }

                        else -> 0.dp
                    }

                Log.d(
                    "GRID_DEBUG",
                    """
                    cell=${cell.index}
                    x=${cell.x}
                    y=${cell.y}

                    rowWidth=$rowWidth
                    rowOffset=$rowOffset
                    """.trimIndent()
                )

                CellGeometry(
                    index = cell.index,
                    left =
                        horizontalIndent +
                                rowOffset +
                                margin +
                                cellPitch * cell.x.toFloat(),

                    top =
                        verticalIndent +
                                margin +
                                cellPitch * cell.y.toFloat()
                )
            }

    cellParams.forEach { geometry ->

        Log.d(
            "GRID_DEBUG",
            """
            geometry:
            index=${geometry.index}
            left=${geometry.left}
            top=${geometry.top}
            right=${geometry.left + buttonDiameter}
            bottom=${geometry.top + buttonDiameter}
            """.trimIndent()
        )
    }

    val minLeft =
        cellParams.minOf { it.left }

    val maxRight =
        cellParams.maxOf {
            it.left + buttonDiameter
        }

    val minTop =
        cellParams.minOf { it.top }

    val maxBottom =
        cellParams.maxOf {
            it.top + buttonDiameter
        }

    Log.d(
        "GRID_DEBUG",
        """
        =================================================
        OCCUPIED AREA

        minLeft=$minLeft
        maxRight=$maxRight

        minTop=$minTop
        maxBottom=$maxBottom

        occupiedWidth=${maxRight - minLeft}
        occupiedHeight=${maxBottom - minTop}

        availableWidth=$availableWidth
        availableHeight=$availableHeight
        =================================================
        """.trimIndent()
    )

    return GameGridParams(
        buttonDiameter = buttonDiameter,
        buttonRadius = buttonDiameter / 2,
        margin = margin,
        cells = cellParams
    )
}


/*
fun calculateGameGridParams(
    session: GameSession,
    availableWidth: Dp,
    availableHeight: Dp
): GameGridParams {

    val topology =
        session.profile.activeTopology
    val topologyWidth =
        topology.spaceWidth

    val topologyHeight =
        topology.spaceHeight


    Log.d(
        "GRID_DEBUG",
        """
    =================================================
    availableWidth=$availableWidth
    availableHeight=$availableHeight

    topology=${topology.code}
    topologyWidth=$topologyWidth
    topologyHeight=$topologyHeight
    =================================================
    """.trimIndent()
    )



    val gapRatio = 0.10f



    val SAFETY_MARGIN = 1.dp

    val virtualSize =
        min(
            availableWidth / topologyWidth,
            availableHeight / topologyHeight
        ) - SAFETY_MARGIN

    val horizontalIndent =
        (availableWidth -
                virtualSize * topologyWidth) / 2

    val verticalIndent =
        (availableHeight -
                virtualSize * topologyHeight) / 2

    val margin =
        virtualSize * gapRatio

    val buttonDiameter =
        virtualSize - margin * 2

    val cellPitch =
        buttonDiameter + margin * 2

    Log.d(
        "GRID_DEBUG",
        """
    virtualSize=$virtualSize
    horizontalIndent=$horizontalIndent
    verticalIndent=$verticalIndent

    gapRatio=$gapRatio
    margin=$margin

    buttonDiameter=$buttonDiameter
    buttonRadius=${buttonDiameter / 2}
    cellPitch=$cellPitch
    """.trimIndent()
    )

    val cellParams =
        topology.cells
            .filter { !it.blocked }
            .map { cell ->

                val rowCells =
                    topology.cells
                        .filter {
                            !it.blocked &&
                                    it.y == cell.y
                        }

                val rowWidth =
                    rowCells.maxOf { it.x } + 1

                val rowOffset =
                    when (
                        topology.projection.horizontalAlignment
                    ) {
                        "CENTER" -> {
                            (
                                    topologyWidth -
                                            rowWidth
                                    ) * cellPitch / 2f
                        }

                        else -> 0.dp
                    }
                Log.d(
                    "GRID_DEBUG",
                    """
    cell=${cell.index}
    x=${cell.x}
    y=${cell.y}

    rowWidth=$rowWidth
    rowOffset=$rowOffset
    """.trimIndent()
                )


                CellGeometry(
                    index = cell.index,
                    left =
                        horizontalIndent +
                                rowOffset +
                                margin +
                                cellPitch * cell.x.toFloat(),
                    top =
                        verticalIndent +
                                margin +
                                cellPitch * cell.y.toFloat()
                )
            }
    Log.d(
        "GRID_DEBUG",
        """
    =================================================
    OCCUPIED AREA

    minLeft=$minLeft
    maxRight=$maxRight

    minTop=$minTop
    maxBottom=$maxBottom

    occupiedWidth=${maxRight - minLeft}
    occupiedHeight=${maxBottom - minTop}

    availableWidth=$availableWidth
    availableHeight=$availableHeight
    =================================================
    """.trimIndent()
    )
    return GameGridParams(
        buttonDiameter = buttonDiameter,
        buttonRadius = buttonDiameter / 2,
        margin = margin,
        cells = cellParams
    )
}

 */