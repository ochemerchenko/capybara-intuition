package com.capybara.intuition.core.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object ChartTypography {

    val yAxisTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Companion.SemiBold,
        fontStyle = FontStyle.Companion.Italic,
        fontSize = 22.sp
    )

    val axisLabel = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Companion.Normal,
        fontSize = 12.sp
    )

    val chartTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Companion.SemiBold,
        fontSize = 18.sp
    )
}