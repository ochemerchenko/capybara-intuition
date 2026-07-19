package com.capybara.intuition.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.game.data.local.db.entity.GameModeEntity

@Dao
interface GameModeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        modes: List<GameModeEntity>
    )

    @Query("SELECT COUNT(*) FROM game_modes")
    suspend fun count(): Int
}