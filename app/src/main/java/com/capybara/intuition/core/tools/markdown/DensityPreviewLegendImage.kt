package com.capybara.intuition.core.tools.markdown

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
fun DensityPreviewLegendImage(
    modifier: Modifier = Modifier
) {

    val densityPreviewLegendImageVerticalPadding = 8.dp
    val densityPreviewLegendImageMaxHeight = 640.dp

    Image(
        painter = painterResource(
            id = R.drawable.help_full_density_chart
        ),

        contentDescription = null,

        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = densityPreviewLegendImageVerticalPadding)
            .heightIn(max = densityPreviewLegendImageMaxHeight),

        contentScale = ContentScale.Fit
    )
}