package com.capybara.intuition.features.game.data.mapper.topology

import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.features.game.domain.model.topology.TopologyFamily

fun TopologyFamilyEntity.toDomain(): TopologyFamily {
    return TopologyFamily(
        id = id,
        code = code
    )
}

