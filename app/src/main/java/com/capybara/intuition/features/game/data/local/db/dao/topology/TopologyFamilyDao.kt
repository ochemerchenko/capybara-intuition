package com.capybara.intuition.features.game.data.local.db.dao.topology

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query(
        """
        SELECT * FROM topology_family_relations
        WHERE familyId = :familyId
        """
    )
    suspend fun getRelationsForFamily(
        familyId: Long
    ): List<TopologyFamilyRelationEntity>

    // Метод нижче під сумнівом, треба передивитись уважно
    @Query(
        """
    SELECT * FROM topology_families
    WHERE id = :id
    LIMIT 1
    """
    )
    suspend fun getFamilyById(
        id: Long
    ): TopologyFamilyEntity?
}