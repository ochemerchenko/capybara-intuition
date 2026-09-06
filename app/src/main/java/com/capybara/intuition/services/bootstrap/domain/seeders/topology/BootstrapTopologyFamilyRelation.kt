package com.capybara.intuition.services.bootstrap.domain.seeders.topology

import com.google.gson.annotations.SerializedName

data class BootstrapTopologyFamilyRelation(

    @SerializedName("family_id")
    val familyId: Long,

    @SerializedName("topology_id")
    val topologyId: Short
)