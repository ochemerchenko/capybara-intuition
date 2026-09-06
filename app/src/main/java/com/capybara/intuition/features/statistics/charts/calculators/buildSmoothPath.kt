package com.capybara.intuition.features.statistics.charts.calculators

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path


fun buildSmoothPath(
    points: List<Offset>
): Path {

    val path = Path()

    if (points.isEmpty()) {
        return path
    }

    if (points.size == 1) {
        path.moveTo(points.first().x, points.first().y)
        return path
    }

    path.moveTo(
        points.first().x,
        points.first().y
    )

    for (i in 0 until points.lastIndex) {

        val p0 =
            if (i == 0) points[i]
            else points[i - 1]

        val p1 = points[i]

        val p2 = points[i + 1]

        val p3 =
            if (i + 2 < points.size)
                points[i + 2]
            else
                points[i + 1]

        val cp1 = Offset(
            x = p1.x + (p2.x - p0.x) / 6f,
            y = p1.y + (p2.y - p0.y) / 6f
        )

        val cp2 = Offset(
            x = p2.x - (p3.x - p1.x) / 6f,
            y = p2.y - (p3.y - p1.y) / 6f
        )

        path.cubicTo(
            cp1.x,
            cp1.y,
            cp2.x,
            cp2.y,
            p2.x,
            p2.y
        )
    }

    return path
}
