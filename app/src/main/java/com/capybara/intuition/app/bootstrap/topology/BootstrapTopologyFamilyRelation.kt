package com.capybara.intuition.app.bootstrap.topology

import com.google.gson.annotations.SerializedName

data class BootstrapTopologyFamilyRelation(

    @SerializedName("family_id")
    val familyId: Long,

    @SerializedName("topology_id")
    val topologyId: Short
)