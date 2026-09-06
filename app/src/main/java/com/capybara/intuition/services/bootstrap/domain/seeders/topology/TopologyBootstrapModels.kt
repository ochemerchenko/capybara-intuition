package com.capybara.intuition.services.bootstrap.domain.seeders.topology

import com.capybara.intuition.services.bootstrap.domain.seeders.topology.TopologyStyleProjection

data class BootstrapTopology(

    val id: Short,
    val code: String,
    val spaceWidth: Int,
    val spaceHeight: Int,
    val occupancyCapacity: Int,
    val projection: TopologyStyleProjection,
    val cells: List<BootstrapTopologyCell>
)