package com.capybara.intuition.features.statistics.presentation.ui.premium.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.presentation.mapper.sessionFilterLabelRes
import com.capybara.intuition.features.statistics.presentation.model.SessionFilter

@Composable
fun PremiumDensityDetailStatisticsHeader(
    onBack: () -> Unit,
    mode: SessionFilter,
    level: Int,
    modifier: Modifier = Modifier
) {
// =====================================================
    // PRESENTATION SEMANTICS
    // =====================================================

    val headerHorizontalPadding = 16.dp
    val headerVerticalPadding = 8.dp
    val helpButtonSize = 20.dp
    val helpIconAlpha = 0.72f

    // =====================================================
    // UI
    // =====================================================

    Row(
        modifier = modifier
            .fillMaxWidth()
        .padding(
            horizontal = headerHorizontalPadding,
            vertical = headerVerticalPadding
        )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(
                    40.dp
                )


        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = IntuitionTheme
                    .colors
                    .textSecondary
                    .copy(alpha = helpIconAlpha),

                )
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
                .size(
                    helpButtonSize
                )
        )

        Text(
            text = stringResource(
                R.string.statistics_density_header,
                level,
                stringResource(sessionFilterLabelRes(mode))
            ),
            style = IntuitionTheme.typography.bodyLarge,
            color = IntuitionTheme.colors.textPrimary
        )
    }
}



