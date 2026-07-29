package com.capybara.intuition.app.navigation.ui.setup

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.app.tools.markdown.MarkdownText
import java.io.IOException


@Composable
fun ReleaseNotesScreen(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {

    val context = LocalContext.current
    val language = context.resources.configuration.locales[0].language

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentPadding = 24.dp
    val contentSpacing = 16.dp

    val asset = "onboarding.md"

    val markdown = remember(asset, language) {

        try {
            context.assets
                .open("onboarding/$language/$asset")
                .bufferedReader()
                .use { it.readText() }

        } catch (e: IOException) {

            context.assets
                .open("onboarding/en/$asset")
                .bufferedReader()
                .use { it.readText() }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .background(
                color = colors.backgroundSection,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(contentSpacing)
        ) {
            MarkdownText(
                markdown = markdown
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeButton,
                    contentColor = colors.activeButtonText,
                    disabledContainerColor = colors.passiveButton,
                    disabledContentColor = colors.passiveButtonText
                )
            ) {
                Text(
                    text = stringResource(R.string.onboarding_button_continue)
                )
            }

        }
    }
}
