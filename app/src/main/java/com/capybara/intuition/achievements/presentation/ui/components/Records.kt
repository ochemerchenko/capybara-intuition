package com.capybara.intuition.achievements.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.capybara.intuition.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Records(
    viewModel: AchievementsViewModel,
    modifier: Modifier,
) {
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val records by viewModel.records.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Рекорди",
            style = typography.h2,
            color = colors.textH2
        )

        if (records.isEmpty()) {
            Text(
                text = "Рекордів поки що немає",
                style = typography.bodyMedium,
                color = colors.activeElementText
            )
        } else {
            records.forEach { achievement ->

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