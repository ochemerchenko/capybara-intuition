package com.capybara.intuition.features.game.domain.renderer

import com.capybara.intuition.features.game.domain.renderer.color.renderer.CheckerRenderer
import com.capybara.intuition.features.game.domain.renderer.color.renderer.SolidFillRenderer
import com.capybara.intuition.features.game.domain.renderer.image.renderer.DirectImageRenderer
import com.capybara.intuition.features.game.presentation.ui.components.gameGrid.MaskIds
import com.capybara.intuition.features.game.presentation.ui.components.gameGrid.RendererIds


fun rendererResolver(
    resourceType: String,
    maskId: Short,
    rendererId: Short
): BetButtonRenderer {

    return when (

        Triple(
            resourceType,
            maskId,
            rendererId
        )

    ) {

        // -------------------------------------------------
        // COLOR
        // -------------------------------------------------


        Triple(
            "COLOR",
            MaskIds.CIRCLE,
            RendererIds.CHECKER
        ) -> CheckerRenderer

        Triple(
            "COLOR",
            MaskIds.CIRCLE,
            RendererIds.SOLID_FILL
        ) -> SolidFillRenderer


        Triple(
            "COLOR",
            MaskIds.SQUARE,
            RendererIds.SOLID_FILL
        ) -> SolidFillRenderer

        Triple(
            "COLOR",
            MaskIds.SQUARE,
            RendererIds.CHECKER
        ) -> CheckerRenderer

        Triple(
            "COLOR",
            MaskIds.STAR,
            RendererIds.SOLID_FILL
        ) -> SolidFillRenderer

        Triple(
            "COLOR",
            MaskIds.TRIANGLE,
            RendererIds.SOLID_FILL
        ) -> SolidFillRenderer

        // -------------------------------------------------
        // IMAGE
        // -------------------------------------------------

        Triple(
            "DRAWABLE",
            MaskIds.SQUARE,
            RendererIds.DIRECT_IMAGE
        ) -> DirectImageRenderer

        Triple(
            "DRAWABLE",
            MaskIds.CIRCLE,
            RendererIds.DIRECT_IMAGE
        ) -> DirectImageRenderer

        else -> error(
            "Unsupported renderer combination: " +
                    "resource=$resourceType, " +
                    "mask=$maskId, " +
                    "renderer=$rendererId"
        )
    }
}