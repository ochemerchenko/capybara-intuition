
package com.capybara.intuition.features.game.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.capybara.intuition.features.game.data.local.db.entity.NotebookEntity
import com.capybara.intuition.features.game.data.local.db.entity.NotebookResourceLinkEntity
import com.capybara.intuition.features.game.data.local.db.entity.ResourceEntity

data class NotebookWithResources(

    @Embedded
    val notebook: NotebookEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",

        associateBy = Junction(
            value = NotebookResourceLinkEntity::class,
            parentColumn = "notebookId",
            entityColumn = "resourceId"
        )
    )
    val resources: List<ResourceEntity>
)
