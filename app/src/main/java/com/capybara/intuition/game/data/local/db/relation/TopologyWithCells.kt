package com.capybara.intuition.game.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyCellEntity
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.game.domain.model.topology.Topology
import com.capybara.intuition.game.domain.model.topology.TopologyCell
import com.capybara.intuition.game.domain.model.topology.TopologyProjection

data class TopologyWithCells(

    @Embedded
    val topology: TopologyEntity,

    @Relation(
        entity = TopologyCellEntity::class,
        parentColumn = "id",
        entityColumn = "topologyId"
    )
    val cells: List<TopologyCellEntity>
)

fun TopologyWithCells.toDomain(): Topology {

    return Topology(
        id = topology.id,
        code = topology.code,
        spaceWidth = topology.spaceWidth,
        spaceHeight = topology.spaceHeight,
        occupancyCapacity = topology.occupancyCapacity,
        projection = TopologyProjection(
            horizontalAlignment = topology.horizontalAlignment,
            verticalAlignment = topology.verticalAlignment,
            scaleMode = topology.scaleMode
        ),
        cells = cells.map { cell ->
            TopologyCell(
                index = cell.index,
                x = cell.x,
                y = cell.y,
                width = cell.width,
                height = cell.height,
                blocked = cell.blocked
            )
        }
    )
}