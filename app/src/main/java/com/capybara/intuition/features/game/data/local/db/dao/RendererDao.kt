package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.game.data.local.db.entity.RendererEntity

@Dao
interface RendererDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        renderers: List<RendererEntity>
    )

    @Query("""
    SELECT * FROM renderers
""")
    suspend fun getAll(): List<RendererEntity>

    @Query("""
    SELECT * FROM renderers
    WHERE id = :id
    LIMIT 1
""")
    suspend fun getById(
        id: String
    ): RendererEntity?
}