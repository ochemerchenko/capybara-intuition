package com.capybara.intuition.features.info.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.tools.markdown.MarkdownAssetLoader
import com.capybara.intuition.core.tools.markdown.MarkdownText
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection
import java.io.IOException

@Composable
fun InfoMarkdownSection(
    modifier: Modifier = Modifier,
    asset: String,
) {
    val context = LocalContext.current
    val contentSpacing = 16.dp

    val build_info_asset = "build_info.md"

    val markdown = remember(asset) {
        MarkdownAssetLoader.load(
            context = context,
            directory = "info",
            fileName = asset
        )
    }

    val buildInfo = remember(build_info_asset) {
        try {
            context.assets
                .open("onboarding/$build_info_asset")
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            ""
        }
    }

    val scrollState = rememberScrollState()

    // Використовуємо SettingsSection для уніфікації вигляду.
    // Оскільки заголовок вже є в Navigation або самому Markdown, тут можемо передати порожній.
    SettingsSection(
        title = "" 
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(contentSpacing)
        ) {
            MarkdownText(
                markdown = markdown
            )

            MarkdownText(
                markdown = buildInfo
            )
        }
    }
}
