package com.capybara.intuition.app.tools.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun MarkdownText(
    markdown: String,
    modifier: Modifier = Modifier,
    onLinkClick: (String) -> Unit = {}
) {

    val uriHandler = LocalUriHandler.current

    fun openExternalUri(uri: String) {
        uriHandler.openUri(uri)
    }

    fun onMarkdownLinkClick(uri: String) {

        if (uri.startsWith("app://")) {
            onLinkClick(uri)
        } else {
            openExternalUri(uri)
        }
    }

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography




    // =====================================================
    // MARKDOWN TEXT PRESENTATION SEMANTICS
    // =====================================================

    val markdownEmptyLineSpacing = 12.dp

    val markdownH1BottomSpacing = 12.dp

    val markdownH2BottomSpacing = 8.dp

    val markdownH3BottomSpacing = 6.dp

    val markdownBulletBottomSpacing = 4.dp

    val markdownParagraphBottomSpacing = 8.dp

    val markdownBulletPrefix = "• "

    // =====================================================
    // STATE
    // =====================================================

    val lines = markdown.lines()

    // =====================================================
    // UI
    // =====================================================

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        lines.forEach { rawLine ->

            val line = rawLine.trim()

            when {

                // 🔹 EMPTY LINE

                line.isBlank() -> {

                    Spacer(
                        modifier = Modifier.height(
                            markdownEmptyLineSpacing
                        )
                    )
                }

                // 🔹 H1

                line.startsWith("# ") -> {

                    Text(
                        text = line.removePrefix("# "),
                        style = typography.h1,
                        color = colors.textH1
                    )

                    Spacer(
                        modifier = Modifier.height(
                            markdownH1BottomSpacing
                        )
                    )
                }

                // 🔹 H2

                line.startsWith("## ") -> {

                    Text(
                        text = line.removePrefix("## "),
                        style = typography.h2,
                        color = colors.textH2
                    )

                    Spacer(
                        modifier = Modifier.height(
                            markdownH2BottomSpacing
                        )
                    )
                }

                // 🔹 H3

                line.startsWith("### ") -> {

                    Text(
                        text = line.removePrefix("### "),
                        style = typography.h3,
                        color = colors.textH3
                    )

                    Spacer(
                        modifier = Modifier.height(
                            markdownH3BottomSpacing
                        )
                    )
                }

                // 🔹 BULLET

                line.startsWith("- ") -> {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = markdownBulletPrefix,
                            style = typography.bodyMedium,
                            color = colors.activeElementText
                        )

                        MarkdownParagraph(
                            text = line.removePrefix("- "),
                            onLinkClick = { uri ->
                                onMarkdownLinkClick(uri)
                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(
                            markdownBulletBottomSpacing
                        )
                    )
                }

                // 🔹 IMAGE TOKEN

                line.startsWith("![") &&
                        line.endsWith("]") -> {

                    val token = line
                        .removePrefix("![")
                        .removeSuffix("]")

                    MarkdownImage(
                        token = token
                    )

                    Spacer(
                        modifier = Modifier.height(
                            markdownH1BottomSpacing
                        )
                    )
                }

                // 🔹 PARAGRAPH

                else -> {

                    MarkdownParagraph(
                        text = line,
                        onLinkClick = { uri ->
                            onMarkdownLinkClick(uri)
                        }

                    )

                    Spacer(
                        modifier = Modifier.height(
                            markdownParagraphBottomSpacing
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun MarkdownParagraph(
    text: String,
    onLinkClick: (String) -> Unit
) {

    val linkRegex = "\\[(.*?)]\\((.*?)\\)".toRegex()
    val boldRegex = "\\*\\*(.*?)\\*\\*".toRegex()
    val italicRegex = "\\*(.*?)\\*".toRegex()

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val annotatedString = buildAnnotatedString {

        val regex =
            "(\\*\\*(.*?)\\*\\*)|(\\*(.*?)\\*)|(\\[(.*?)]\\((.*?)\\))"
                .toRegex()

        var currentIndex = 0

        regex.findAll(text).forEach { match ->

            append(
                text.substring(
                    currentIndex,
                    match.range.first
                )
            )

            when {

                // 🔹 BOLD

                match.groupValues[2].isNotEmpty() -> {

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = colors.textChipsActive
                        )
                    ) {

                        append(match.groupValues[2])
                    }
                }

                // 🔹 ITALIC

                match.groupValues[4].isNotEmpty() -> {

                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {

                        append(match.groupValues[4])
                    }
                }

                // 🔹 LINK

                match.groupValues[6].isNotEmpty() -> {

                    val label = match.groupValues[6]
                    val url = match.groupValues[7]

                    pushStringAnnotation(
                        tag = "LINK",
                        annotation = url
                    )

                    withStyle(
                        SpanStyle(
                            color = IntuitionTheme.colors.accent,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(label)
                    }
                    pop()
                }
            }

            currentIndex = match.range.last + 1
        }

        if (currentIndex < text.length) {
            append(
                text.substring(currentIndex)
            )
        }
    }

    ClickableText(
        text = annotatedString,
        style = typography.bodyMedium.copy(
            color = colors.textChipsActive
        ),
        onClick = { offset ->

            annotatedString
                .getStringAnnotations(
                    tag = "LINK",
                    start = offset,
                    end = offset
                )
                .firstOrNull()
                ?.let { annotation ->

                    onLinkClick(annotation.item)
                }
        }
    )



}