package com.capybara.intuition.features.game.data.local.db.dao.topology

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyCellEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity

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

    @Query(
        """
    SELECT COUNT(*)
    FROM topologies
    """
    )
    suspend fun getTopologiesCount(): Int

}
