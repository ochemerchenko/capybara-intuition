package com.capybara.intuition.features.game.data.local.db.dao.topology

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyCellEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.features.game.data.local.db.relation.TopologyWithCells

@Dao
interface TopologyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopologies(
        topologies: List<TopologyEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopologyCells(
        cells: List<TopologyCellEntity>
    )

    @Transaction
    @Query(
        """
        SELECT * FROM topologies
        WHERE code = :topologyCode
        """
    )
    suspend fun getTopologyWithCells(
        topologyCode: String
    ): TopologyWithCells

    @Query(
        """
        SELECT * FROM topologies
        WHERE occupancyCapacity = :capacity
        """
    )
    suspend fun getTopologiesByCapacity(
        capacity: Int
    ): List<TopologyEntity>

    @Query(
        """
    SELECT COUNT(*)
    FROM topologies
    """
    )
    suspend fun getTopologiesCount(): Int


}