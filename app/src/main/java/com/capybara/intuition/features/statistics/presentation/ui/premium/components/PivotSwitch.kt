package com.capybara.intuition.features.statistics.presentation.ui.premium.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.presentation.ui.model.PivotMode

@Composable
fun PivotSwitch(
    pivot: PivotMode,
    onChange: (PivotMode) -> Unit,
    modifier: Modifier = Modifier
) {
    // =====================================================
    // PIVOT SWITCH PRESENTATION SEMANTICS
    // =====================================================
    val pivotSwitchCornerRadius = 14.dp
    val pivotSwitchOuterPadding = 4.dp

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.backgroundSection,
                shape = RoundedCornerShape(
                    pivotSwitchCornerRadius
                )
            )
            .padding(
                pivotSwitchOuterPadding
            )
    ) {


        PivotItem(
            text = stringResource(
                R.string.statistics_pivot_level_label
            ),
            modifier = Modifier
                .weight(1f)
                .padding(
                    start=16.dp,
                    end=16.dp
                ),
            selected = pivot == PivotMode.LEVEL
        ) {
            onChange(PivotMode.LEVEL)
        }



        PivotItem(
            text = stringResource(
                R.string.statistics_pivot_mode_label
            ),
            modifier = Modifier
                .weight(1f)
                .padding(
                    start=16.dp,
                    end=16.dp
                ),
            selected = pivot == PivotMode.SESSION_FILTER
        ) {
            onChange(PivotMode.SESSION_FILTER)
        }


    }
}


@Composable
private fun PivotItem(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit
) {

    // =====================================================
    // PIVOT ITEM PRESENTATION SEMANTICS
    // =====================================================

    val pivotItemCornerRadius = 10.dp
    val pivotItemHorizontalPadding = 16.dp
    val pivotItemVerticalPadding = 8.dp

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Box(
        modifier = modifier
            .clip(
                androidx.compose.foundation.shape.RoundedCornerShape(
                    pivotItemCornerRadius
                )
            )
            .background(
                if (selected) {
                    colors.backgroundChipsActive
                } else {
                    colors.backgroundChipsPassive
                }
            )
            .clickable(
                enabled = !selected
            ) {
                onClick()
            }
            .padding(
                horizontal = pivotItemHorizontalPadding,
                vertical = pivotItemVerticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme
                .typography
                .bodyMedium,
            color =
                if (selected) {
                    colors.textChipsActive
                } else {
                    colors.textChipsPassive
                }
        )
    }
}