package com.capybara.intuition.services.bootstrap.domain.seeders.topology

data class BootstrapTopologyCell(

    val index: Int,

    val x: Int,
    val y: Int,

    val width: Int,
    val height: Int,

    val blocked: Boolean
)