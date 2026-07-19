package com.capybara.intuition.game.data.mapper

import com.capybara.intuition.game.domain.model.RoundType

object RoundTypeMapper {

    fun fromId(id: Short): RoundType =
        when (id) {
            1.toShort() -> RoundType.AFTER
            2.toShort() -> RoundType.BEFORE
            else -> error("Unknown RoundType id: $id")
        }

    fun toId(type: RoundType): Short =
        when (type) {
            RoundType.AFTER -> 1
            RoundType.BEFORE -> 2
        }
}