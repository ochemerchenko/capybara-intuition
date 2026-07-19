package com.capybara.intuition.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "notebook_resource_links",

    primaryKeys = [
        "notebookId",
        "resourceId"
    ],

    indices = [
        Index("notebookId"),
        Index("resourceId")
    ],

    foreignKeys = [

        ForeignKey(
            entity = NotebookEntity::class,
            parentColumns = ["id"],
            childColumns = ["notebookId"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = ResourceEntity::class,
            parentColumns = ["id"],
            childColumns = ["resourceId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookResourceLinkEntity(

    /**
     * Canonical notebook semantic identity.
     */
    val notebookId: Short,

    /**
     * Canonical resource semantic identity.
     */
    val resourceId: Short
)