package com.capybara.intuition.features.profile.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.capybara.intuition.features.game.data.local.db.entity.GameModeEntity
import com.capybara.intuition.features.game.data.local.db.entity.NotebookEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.services.installation.data.local.db.entity.InstallationEntity
import com.capybara.intuition.features.profile.domain.model.ProfileType

@Entity(
    tableName = "profiles",

    foreignKeys = [

        ForeignKey(
            entity = InstallationEntity::class,
            parentColumns = ["id"],
            childColumns = ["installationId"]
        ),

        ForeignKey(
            entity = TopologyFamilyEntity::class,
            parentColumns = ["id"],
            childColumns = ["topologyFamilyId"]
        ),

        ForeignKey(
            entity = TopologyEntity::class,
            parentColumns = ["id"],
            childColumns = ["activeTopologyId"]
        ),

        ForeignKey(
            entity = NotebookEntity::class,
            parentColumns = ["id"],
            childColumns = ["activeNotebookId"]
        ),

        ForeignKey(
            entity = GameModeEntity::class,
            parentColumns = ["id"],
            childColumns = ["preferredMode"]
        )
    ],

    indices = [
        Index("installationId"),
        Index("topologyFamilyId"),
        Index("activeTopologyId"),
        Index("activeNotebookId"),
        Index("preferredMode")
    ]
)
data class ProfileEntity(

    // =================================================
    // IDENTITY
    // =================================================

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * Human-readable profile name.
     */
    val name: String,

    /**
     * Child runtime profile marker.
     */
    val type: ProfileType,

    /**
     * Stable anonymous installation owner.
     */
    val installationId: String,

    /**
     * Immutable bootstrap-owned
     * gameplay defaults profile.
     *
     * Exactly one profile
     * in installation must have:
     *
     * isSystemDefault == true
     */
    val isSystemDefault: Boolean,

    // =================================================
    // SECURITY
    // =================================================

    /**
     * Optional SHA-256 hash
     * of 4-digit parent PIN.
     *
     * Null means:
     * no protection configured.
     */
    val pinHash: String?,

    // =================================================
    // GAMEPLAY IDENTITY
    // =================================================

    /**
     * FK -> notebooks.id
     *
     * Currently selected notebook.
     */
    val activeNotebookId: Short,

    /**
     * Current gameplay mode.
     */
    val preferredMode: Short,

    /**
     * Immutable progression lower bound.
     *
     * Bootstrap-defined.
     */
    val minLevel: Int,

    /**
     * Immutable progression upper bound.
     *
     * Bootstrap-defined.
     */
    val maxLevel: Int,

    /**
     * Mutable current progression position.
     *
     * Must always satisfy:
     *
     * minLevel <= currentLevel <= maxLevel
     */
    val currentLevel: Int,

    /**
     * Canonical renderer dictionary reference.
     */
    val rendererId: Short,

    /**
     * Canonical mask dictionary reference.
     */
    val maskId: Short,

    /**
     * Currently topology family id.
     */
    val topologyFamilyId: Long,

    val maxTopologyWidth: Int,
    val maxTopologyHeight: Int,

    /**
     * Currently topology code.
     */
    val activeTopologyId: Short,

    // =================================================
    // LIFECYCLE
    // =================================================

    /**
     * Soft-delete marker.
     *
     * Deleted profiles disappear
     * from active usage,
     * but historical ownership
     * remains intact.
     */
    val isDeleted: Boolean,

    /**
     * Currently active runtime profile.
     */
    val active: Boolean,

    /**
     * Technical metadata.
     */
    val createdAt: Long,

    /**
     * Last profile usage timestamp.
     */
    val lastUsedAt: Long
)