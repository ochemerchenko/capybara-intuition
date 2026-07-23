package com.capybara.intuition.info.presentation.ui


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.app.ExitDialog
import com.capybara.intuition.app.presentation.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.info.presentation.ui.components.InfoMarkdownSection
import com.capybara.intuition.info.presentation.ui.components.InfoNavigation
import com.capybara.intuition.info.presentation.ui.components.InfoSectionType



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


    // End of BACK-dialog

    Column(
        modifier = Modifier
            .padding(
                env.screenContentPadding
            ),
        verticalArrangement =
            Arrangement.spacedBy(
                env.screenSectionSpacing
            )
    ) {
        InfoNavigation(
            modifier = Modifier,
            selectedSection = selectedSection,
            onSectionSelected = {
                selectedSection = it
            }
        )

        when (selectedSection) {

            InfoSectionType.ABOUT ->InfoMarkdownSection(
                modifier = Modifier,
                asset = "about.md"
            )


            InfoSectionType.PRIVACY -> InfoMarkdownSection(
                modifier = Modifier,
                asset = "privacy.md"
            )

            InfoSectionType.TERMS -> InfoMarkdownSection(
                modifier = Modifier,
                asset = "terms.md"
            )
        }

    }

 }