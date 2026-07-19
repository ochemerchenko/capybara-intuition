package com.capybara.intuition.game.domain.model.topology

data class Topology(

    val id: Short,
    val code: String,
    val spaceWidth: Int,
    val spaceHeight: Int,
    val occupancyCapacity: Int,
    val projection: TopologyProjection,
    val cells: List<TopologyCell>,

)