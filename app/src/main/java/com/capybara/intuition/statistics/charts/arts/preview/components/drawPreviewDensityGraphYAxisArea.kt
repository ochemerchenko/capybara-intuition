package com.capybara.intuition.statistics.charts.arts.preview.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.PreviewDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawPreviewDensityGraphYAxisArea(
    layoutModel: Area,
    yScaleModel: PreviewDensityYScaleModel,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    for (
    value in yScaleModel.axisMin..
            yScaleModel.axisMax
            step yScaleModel.tickStep
    ) {

        val ratio =
            (value - yScaleModel.axisMin).toFloat() /
                    (yScaleModel.axisMax - yScaleModel.axisMin)


        val textLayout = textMeasurer.measure(
            text = value.toString(),
            style = typography.chartAxisLabel.copy(
                color = colors.chartAxisLabel
            )
        )

        drawText(
            textLayoutResult = textLayout,
            topLeft = Offset(
                x = layoutModel.left +
                        layoutModel.width -
                        textLayout.size.width,

                y = layoutModel.top +
                        layoutModel.height -
                        ratio * layoutModel.height -
                        textLayout.size.height / 2f
            )
        )
    }
}