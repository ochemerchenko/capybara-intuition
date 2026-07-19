package com.capybara.intuition.app.tools.markdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R


@Composable
fun ActivityLegendImage(
    modifier: Modifier = Modifier
) {

    val activityLegendImageVerticalPadding = 8.dp
    val activityLegendImageMaxHeight = 640.dp

    Image(
        painter = painterResource(
            id = R.drawable.help_activity_chart
        ),

        contentDescription = null,

        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = activityLegendImageVerticalPadding)
            .heightIn(max = activityLegendImageMaxHeight),

        contentScale = ContentScale.Fit
    )
}