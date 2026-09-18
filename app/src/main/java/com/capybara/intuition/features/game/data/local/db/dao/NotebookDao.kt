package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.capybara.intuition.features.game.data.local.db.entity.NotebookEntity
import com.capybara.intuition.features.game.data.local.db.relation.NotebookWithResources

@Dao
interface NotebookDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        notebooks: List<NotebookEntity>
    )

    @Query(
        """
        SELECT * FROM notebooks
        """
    )
    suspend fun getAll(): List<NotebookEntity>

    @Transaction
    @Query(
        """
        SELECT * FROM notebooks
        WHERE id = :id
        """
    )
    suspend fun getNotebookWithResources(
        id: Short
    ): NotebookWithResources?

}
