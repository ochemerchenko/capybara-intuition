package com.capybara.intuition.features.statistics.charts.calculators

import com.capybara.intuition.features.statistics.charts.model.EmaTarget
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun enrichWithEma(
    chartPoints: List<AnalyticsChartPoint>,
    target: EmaTarget,
    emaModelingPeriod: Int
): List<AnalyticsChartPoint> {

    return when (target) {
        EmaTarget.USER_LINE -> {
            val emaValues = calculateEma(
                values = chartPoints.map {
                    it.userLine
                },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(
                    userLineEma = emaValues[index]
                )
            }
        }

        EmaTarget.UPPER_CORRIDOR -> {
            val emaValues = calculateEma(
                values = chartPoints.map {
                    it.upperCorridorLine
                },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(
                    upperCorridorLineEma = emaValues[index]
                )
            }
        }

        EmaTarget.LOWER_CORRIDOR -> {
            val emaValues = calculateEma(
                values = chartPoints.map {
                    it.lowerCorridorLine
                },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(
                    lowerCorridorLineEma = emaValues[index]
                )
            }
        }

        EmaTarget.DECISION_TIME -> {
            val emaValues = calculateEma(
                values = chartPoints.map {
                    it.averageDayDecisionTime
                },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(
                    averageDayDecisionTimeEma = emaValues[index]
                )
            }

        }

        EmaTarget.PHYSICAL -> {
            val emaValues = calculateEma(
                values = chartPoints.map { it.physical },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(physicalEma = emaValues[index])
            }
        }

        EmaTarget.HAPPINESS -> {
            val emaValues = calculateEma(
                values = chartPoints.map { it.happiness },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(happinessEma = emaValues[index])
            }
        }

        EmaTarget.CONFIDENCE -> {
            val emaValues = calculateEma(
                values = chartPoints.map { it.confidence },
                period = emaModelingPeriod
            )
            chartPoints.mapIndexed { index, point ->
                point.copy(confidenceEma = emaValues[index])
            }
        }
    }
}
