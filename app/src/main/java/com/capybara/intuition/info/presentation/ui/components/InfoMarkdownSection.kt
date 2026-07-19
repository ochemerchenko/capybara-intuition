package com.capybara.intuition.info.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.app.tools.markdown.MarkdownText
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
        MarkdownText(
            markdown = markdown
        )
    }
}