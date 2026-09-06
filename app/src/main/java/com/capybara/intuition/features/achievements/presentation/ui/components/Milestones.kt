package com.capybara.intuition.features.achievements.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capybara.intuition.features.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun Milestones (
    viewModel: AchievementsViewModel,
    modifier: Modifier
){

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val milestones by viewModel.records.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Рубежі",
            style = typography.h2,
            color = colors.textH2
        )

        if (milestones.isEmpty()) {
            Text(
                text = "Ніякі рубежі не подолані",
                style = typography.bodyMedium,
                color = colors.activeElementText
            )
        } else {
            milestones.forEach { achievement ->

                val style = if (achievement.new) {
                    typography.bodyMediumBold
                } else {
                    typography.bodyMedium
                }

                Row {
                    Text(
                        text = achievement.name,
                        style = style,
                        color = colors.activeElementText,
                    )

                    Spacer(
                        Modifier.weight(1f)
                    )

                    Text(
                        text = achievement.value.toString(),
                        style = style,
                        color = colors.textH2,
                    )
                }
            }
        }
    }

}