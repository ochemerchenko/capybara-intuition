package com.capybara.intuition.game.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notebooks"
)
data class NotebookEntity(

    /**
     * Stable internal identifier.
     *
     * Assigned during bootstrap.
     * Never changes.
     */
    @PrimaryKey
    val id: Short,

    /**
     * Canonical semantic code.
     *
     * Examples:
     * - DEFAULT
     * - CAPYBARA
     *
     * Immutable.
     */
    @ColumnInfo(index = true)
    val code: String,

    /**
     * Null for shipped system notebooks.
     *
     * Non-null for user-created notebooks.
     */
    val createdByProfileId: String?,

    /**
     * Defines whether notebook
     * can be modified by user.
     */
    val isEditable: Boolean
)