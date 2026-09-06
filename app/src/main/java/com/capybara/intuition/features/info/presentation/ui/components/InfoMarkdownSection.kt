package com.capybara.intuition.features.info.presentation.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.core.tools.markdown.MarkdownText
import java.io.IOException

@Composable
fun InfoMarkdownSection(
    modifier: Modifier = Modifier,
    asset: String,
) {

    val context = LocalContext.current
    val language = context.resources.configuration.locales[0].language

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentPadding = 24.dp
    val contentSpacing = 16.dp

    val build_info_asset = "build_info.md"

    val markdown = remember(asset, language) {

        try {
            context.assets
                .open("info/$language/$asset")
                .bufferedReader()
                .use { it.readText() }

        } catch (e: IOException) {

            context.assets
                .open("info/en/$asset")
                .bufferedReader()
                .use { it.readText() }
        }
    }

    val buildInfo = remember(build_info_asset) {
        context.assets
            .open("onboarding/$build_info_asset")
            .bufferedReader()
            .use { it.readText() }
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

            MarkdownText(
                markdown = buildInfo
            )
        }
    }
}