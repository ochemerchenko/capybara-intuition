package com.capybara.intuition.features.achievements.presentation.ui.components.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun achievement (
    string_id: Int,
    value: Long
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Row(

    ){
        Text(
            text = stringResource(string_id),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = value.toString(),
            style = typography.bodyMedium,
            color = colors.activeElementText
        )
    }
}