package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.game.data.local.db.entity.NotebookResourceLinkEntity

@Dao
interface NotebookResourceLinkDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        links: List<NotebookResourceLinkEntity>
    )

    @Query("""
    SELECT COUNT(*)
    FROM notebook_resource_links
""")
    suspend fun count(): Int
}
