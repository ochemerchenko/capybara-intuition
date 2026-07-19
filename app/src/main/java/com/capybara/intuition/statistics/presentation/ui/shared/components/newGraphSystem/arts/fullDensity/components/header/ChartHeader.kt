package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.statistics.presentation.ui.shared.components.dialogs.MarkdownDialog

@Composable
fun ChartHeader (
    title: String,
    helpBaseName: String,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // CHART HEADER PRESENTATION SEMANTICS
    // =====================================================

    val helpButtonSize = 20.dp
    val helpIconAlpha = 0.45f

    var showHelp by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = typography.chartTitle.copy(
                color = colors.chartTitle
            )
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {
                showHelp = true
            },
            modifier = Modifier.size(helpButtonSize)
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
                contentDescription = null,
                tint = colors.textSecondary.copy(
                    alpha = helpIconAlpha
                ),
                modifier = Modifier.size(helpButtonSize)
            )
        }
    }

    if (showHelp) {
        MarkdownDialog(
            baseName = helpBaseName,
            onLinkClick = { url ->
                showHelp = false
                onHelpLinkClick(url)
            },
            onDismiss = {
                showHelp = false
            }
        )
    }
}

/*
* val helpBaseName = when (pivotMode) {

        PivotMode.LEVEL ->
            "density_chart_level_legend"

        PivotMode.SESSION_FILTER ->
            "density_chart_mode_legend"
    }*/