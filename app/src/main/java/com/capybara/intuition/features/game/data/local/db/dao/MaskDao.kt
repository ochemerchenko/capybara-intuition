package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.capybara.intuition.features.game.data.local.db.entity.MaskEntity

@Dao
interface MaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        masks: List<MaskEntity>
    )
}
