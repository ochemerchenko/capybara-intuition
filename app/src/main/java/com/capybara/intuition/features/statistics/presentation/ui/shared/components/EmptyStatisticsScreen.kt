package com.capybara.intuition.features.statistics.presentation.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun EmptyStatisticsScreen() {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundScreen),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(colors.backgroundBlock)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = stringResource(R.string.statistics_empty_screen_title),
                style = typography.titleMedium,
                color = colors.activeElementText
            )

            Text(
                text = stringResource(R.string.statistics_empty_screen_text),
                style = typography.bodyMedium,
                color = colors.activeElementText
            )
        }
    }
}