package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.game.data.local.db.entity.MaskEntity

@Dao
interface MaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        masks: List<MaskEntity>
    )

    @Query("""
    SELECT * FROM masks
""")
    suspend fun getAll(): List<MaskEntity>

    @Query("""
    SELECT * FROM masks
    WHERE id = :id
    LIMIT 1
""")
    suspend fun getById(
        id: Short
    ): MaskEntity?
}