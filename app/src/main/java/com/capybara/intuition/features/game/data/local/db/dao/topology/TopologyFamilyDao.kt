package com.capybara.intuition.features.game.data.local.db.dao.topology

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyRelationEntity

@Dao
interface TopologyFamilyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFamilies(
        families: List<TopologyFamilyEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelations(
        relations: List<TopologyFamilyRelationEntity>
    )
}
