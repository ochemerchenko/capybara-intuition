package com.capybara.intuition.profile.presentation.ui.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection

@Composable
fun ProfileList(
    profiles: List<Profile>,
    onProfileSelected: (Profile) -> Unit,
) {

    // =====================================================
    // PROFILE LIST PRESENTATION SEMANTICS
    // =====================================================
    val profileListOuterSpacing = 12.dp
    val profileListItemsSpacing = 8.dp
    val profileListItemCornerRadius = 16.dp
    val profileListItemPadding = 12.dp

    // =====================================================
    // STATE
    // =====================================================
    val activeProfile = profiles.first {
        it.active
    }

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // UI
    // =====================================================

    SettingsSection(
        title = stringResource(
            R.string.settings_profile_section_title,
            profiles.size
        )
    ) {

        Column(
            verticalArrangement =
                Arrangement.spacedBy(
                    profileListOuterSpacing
                )
        ) {

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(
                        profileListItemsSpacing
                    )
            ) {

                profiles.forEach { profile ->

                    val isActive = profile.id == activeProfile.id
                    var surfaceBackgroundColor = colors.backgroundPassiveRowControl
                    var textColor = colors.passiveElementText
                    var nameTypography = typography.bodyMedium

                    if (isActive) {
                        surfaceBackgroundColor = colors.backgroundActiveRowControl
                        textColor = colors.activeElementText
                        nameTypography = typography.bodyMediumBold
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(
                            profileListItemCornerRadius
                        ),
                        color = surfaceBackgroundColor
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onProfileSelected(
                                        profile
                                    )
                                }
                                .padding(
                                    profileListItemPadding
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column {

                                Text(
                                    text = profile.name,
                                    color = textColor,
                                    style = nameTypography
                                )

                                Text(
                                    text =
                                        when (profile.type) {
                                            ProfileType.HUMAN -> stringResource(
                                                R.string.settings_profile_list_owner
                                            )

                                            ProfileType.CHILD -> stringResource(
                                                R.string.settings_profile_list_child
                                            )

                                            ProfileType.TESTER -> stringResource(
                                                R.string.settings_profile_list_tester
                                            )

                                            ProfileType.SYSTEM ->
                                                error(
                                                    "SYSTEM profile must not be displayed"
                                                )
                                        },
                                    style = MaterialTheme
                                        .typography
                                        .bodySmall,
                                    color = textColor
                                )
                            }

                            if (isActive) {
                                Text(
                                    text = stringResource(
                                        R.string.settings_profile_list_active
                                    ),
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
