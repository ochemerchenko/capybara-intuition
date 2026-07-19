package com.capybara.intuition.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.game.data.local.db.entity.ResourceEntity

@Dao
interface ResourceDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        resource: ResourceEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        resources: List<ResourceEntity>
    )

    @Query(
        """
        SELECT * FROM resources
        WHERE name = :name
        """
    )
    suspend fun getByName(
        name: String
    ): ResourceEntity?

    @Query(
        """
        SELECT * FROM resources
        ORDER BY name
        """
    )
    suspend fun getAll(): List<ResourceEntity>

    @Query(
        """
        DELETE FROM resources
        """
    )
    suspend fun deleteAll()
}