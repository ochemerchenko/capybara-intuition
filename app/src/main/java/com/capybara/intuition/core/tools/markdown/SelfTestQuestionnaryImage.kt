package com.capybara.intuition.core.tools.markdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SelfTestQuestionnaryImage(
    resId: Int,
    modifier: Modifier = Modifier
) {
    val imageVerticalPadding = 8.dp

    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = imageVerticalPadding),
        contentScale = ContentScale.FillWidth
    )
}
