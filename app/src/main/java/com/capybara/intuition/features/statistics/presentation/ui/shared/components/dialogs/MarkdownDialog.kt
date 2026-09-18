package com.capybara.intuition.features.statistics.presentation.ui.shared.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.core.tools.markdown.MarkdownAssetLoader
import com.capybara.intuition.core.tools.markdown.MarkdownText


@Composable
fun MarkdownDialog(
    baseName: String,
    onLinkClick: (String) -> Unit = {},
    onDismiss: () -> Unit
) {

    val colors = IntuitionTheme.colors

    // =====================================================
    // MARKDOWN DIALOG PRESENTATION SEMANTICS
    // =====================================================

    val markdownDialogContentPadding = 20.dp
    val markdownDialogMaxContentHeight = 640.dp
    val markdownDialogFooterTopPadding = 12.dp

    val markdownDialogConfirmButtonText = stringResource(R.string.statistics_markdown_help_close_button)

    // =====================================================
    // STATE
    // =====================================================

    val context = LocalContext.current

    val markdown = remember(baseName) {
        MarkdownAssetLoader.load(
            context = context,
            directory = "help",
            fileName = "$baseName.md",
            fallbackLanguage = "en"
        )
    }

    // =====================================================
    // UI
    // =====================================================

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            color = colors.capybaraPaper

        ) {

            Column(
                modifier = Modifier
                    .padding(
                        markdownDialogContentPadding
                    )
            ) {

                // =========================================
                // SCROLLABLE CONTENT
                // =========================================
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(
                            max =
                                markdownDialogMaxContentHeight
                        )
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    MarkdownText(
                        markdown = markdown,
                        onLinkClick = onLinkClick
                    )
                }

                // =========================================
                // FOOTER
                // =========================================
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = markdownDialogFooterTopPadding
                        ),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colors.activeElementBackground,
                            contentColor = colors.activeElementText
                        ),
                        shape =  RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = markdownDialogConfirmButtonText,
                        )
                    }
                }
            }
        }
    }
}
