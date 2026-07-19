package com.capybara.intuition.profile.tester.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun HistoricalDatasetGroup(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = title,
            style = typography.h3,
            color = colors.textH3
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = colors.backgroundBlock
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                content = content
            )
        }
    }
}