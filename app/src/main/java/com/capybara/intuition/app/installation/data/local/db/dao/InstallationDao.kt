package com.capybara.intuition.app.installation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.capybara.intuition.app.installation.data.local.db.entity.InstallationEntity

@Dao
interface InstallationDao {

    // =========================================================
    // INSERT
    // =========================================================

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        installation: InstallationEntity
    )

    // =========================================================
    // UPDATE
    // =========================================================

    @Update
    suspend fun update(
        installation: InstallationEntity
    )

    // =========================================================
    // QUERIES
    // =========================================================

    @Query(
        """
        SELECT *
        FROM installations
        LIMIT 1
        """
    )
    suspend fun getInstallation(): InstallationEntity

    @Query(
        """
        SELECT COUNT(*)
        FROM installations
        """
    )
    suspend fun countInstallations(): Int




    // =========================================================
    // MAINTENANCE
    // =========================================================

    @Query(
        """
        DELETE FROM installations
        """
    )
    suspend fun deleteAll()
}