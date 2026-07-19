package com.capybara.intuition.profile.tester.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.app.presentation.theme.IntuitionTheme.colors



@OptIn(
    ExperimentalLayoutApi::class
)
@Composable
fun <T> TesterChipSelector(
    title: String,
    values: List<T>,
    selectedValue: T,
    labelProvider: @Composable (T) -> String = {
        it.toString()
    },
    onValueSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Column(
        verticalArrangement = Arrangement.spacedBy(
            8.dp
        )
    ) {

        Text(
            text = title,
            color = colors.textH3,
            style = typography.h3
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            values.forEach { value ->
                FilterChip(
                    selected = value == selectedValue,
                    onClick = {
                        onValueSelected(
                            value
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = colors.backgroundChipsPassive,
                        selectedContainerColor = colors.backgroundChipsActive,
                        labelColor = colors.textChipsPassive,
                        selectedLabelColor = colors.textChipsActive
                    ),
                    border = null,
                    label = {
                        Text(
                            text = labelProvider(
                                value
                            ),
                        )
                    }
                )
            }
        }
    }
}

