package com.capybara.intuition.features.info.presentation.ui


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.core.ui.ExitDialog
import com.capybara.intuition.core.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.features.info.presentation.ui.components.InfoMarkdownSection
import com.capybara.intuition.features.info.presentation.ui.components.InfoNavigation
import com.capybara.intuition.features.info.presentation.ui.components.InfoSectionType

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier
) {
    val env = LocalAppLayoutEnvironment.current
    var selectedSection by remember {
        mutableStateOf(InfoSectionType.ABOUT)
    }

    // Start of BACK-dialog
    var showExitDialog by rememberSaveable { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        ExitDialog(
            onConfirm = {
                activity?.finish()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }

    // Уніфікація з Settings:
    // 1. Root Box з падінгом env.screenContentPadding (16.dp).
    // 2. Ігноруємо вхідний modifier (уникаємо double padding).
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(env.screenContentPadding),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(env.screenSectionSpacing)
        ) {
            InfoNavigation(
                modifier = Modifier,
                selectedSection = selectedSection,
                onSectionSelected = {
                    selectedSection = it
                }
            )

            when (selectedSection) {
                InfoSectionType.ABOUT -> InfoMarkdownSection(
                    asset = "about.md"
                )
                InfoSectionType.PRIVACY -> InfoMarkdownSection(
                    asset = "privacy.md"
                )
                InfoSectionType.TERMS -> InfoMarkdownSection(
                    asset = "terms.md"
                )
            }
        }
    }
}
