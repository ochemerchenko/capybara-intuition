package com.capybara.intuition.features.game.domain.model.topology

data class TopologyCell(

    val index: Int,

    val x: Int,
    val y: Int,

    val width: Int,
    val height: Int,

    val blocked: Boolean
)