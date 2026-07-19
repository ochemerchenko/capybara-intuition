package com.capybara.intuition.app.bootstrap.topology

import com.capybara.intuition.app.bootstrap.topology.TopologyStyleProjection

data class BootstrapTopology(

    val id: Short,
    val code: String,
    val spaceWidth: Int,
    val spaceHeight: Int,
    val occupancyCapacity: Int,
    val projection: TopologyStyleProjection,
    val cells: List<BootstrapTopologyCell>
)