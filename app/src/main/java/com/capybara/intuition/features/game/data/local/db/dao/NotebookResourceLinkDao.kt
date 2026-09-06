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
    suspend fun insert(
        link: NotebookResourceLinkEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        links: List<NotebookResourceLinkEntity>
    )

    @Query(
        """
        SELECT * FROM notebook_resource_links
        WHERE notebookId = :notebookId
        """
    )
    suspend fun getByNotebookName(
        notebookId: Short
    ): List<NotebookResourceLinkEntity>

    @Query(
        """
        SELECT * FROM notebook_resource_links
        WHERE resourceId = :resourceId
        """
    )
    suspend fun getByResourceName(
        resourceId: Short
    ): List<NotebookResourceLinkEntity>

    @Query(
        """
        DELETE FROM notebook_resource_links
        """
    )
    suspend fun deleteAll()

    @Query("""
    SELECT COUNT(*)
    FROM notebook_resource_links
""")
    suspend fun count(): Int
}