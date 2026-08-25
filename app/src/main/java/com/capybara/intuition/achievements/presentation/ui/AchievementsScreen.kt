package com.capybara.intuition.achievements.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.capybara.intuition.achievements.presentation.ui.components.Milestones
import com.capybara.intuition.achievements.presentation.ui.components.Records
import com.capybara.intuition.achievements.presentation.ui.components.Regularity
import com.capybara.intuition.achievements.presentation.ui.components.Skill
import com.capybara.intuition.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.app.presentation.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun AchievementsScreen (
    viewModel: AchievementsViewModel,
    modifier: Modifier
){

    val env = LocalAppLayoutEnvironment.current
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    DisposableEffect(Unit) {
        onDispose {
            viewModel.markAllAsSeen()
        }
    }

    Box(
        modifier = Modifier
            .padding(
                env.screenContentPadding
            ),
        contentAlignment = Alignment.TopStart
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(
                env.screenSectionSpacing
            )
        ) {

            Text(
                text = "Achievement Screen",
                style = typography.h1,
                color = colors.textH1
            )



            Records(
                viewModel = viewModel,
                modifier = modifier
            )

            Regularity(
                viewModel = viewModel,
                modifier = modifier
            )



            Skill(
                viewModel = viewModel,
                modifier = modifier
            )


            Milestones(
                viewModel = viewModel,
                modifier = modifier
            )
        }
    }

}