package com.capybara.intuition.features.info.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import androidx.compose.foundation.lazy.items

@Composable
fun InfoNavigation(
    selectedSection: InfoSectionType,
    onSectionSelected: (InfoSectionType) -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.backgroundSection,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
            horizontalArrangement = Arrangement
                .spacedBy(12.dp)
        ) {

            items(InfoSectionType.entries) { section ->

                FilterChip(
                    selected = section == selectedSection,

                    onClick = {
                        onSectionSelected(section)
                    },

                    label = {
                        Text(
                            text = stringResource(
                                when (section) {
                                    InfoSectionType.ABOUT -> R.string.info_about
                                    InfoSectionType.PRIVACY -> R.string.info_privacy
                                    InfoSectionType.TERMS -> R.string.info_terms
                                }
                            )
                        )
                    },

                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colors.backgroundChipsActive,
                        containerColor = colors.backgroundChipsPassive,
                        selectedLabelColor = colors.textChipsActive,
                        labelColor = colors.textChipsPassive,
                    ),

                    border = null
                )
            }
        }
    }
}