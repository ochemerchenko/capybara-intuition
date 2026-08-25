package com.capybara.intuition.app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.capybara.intuition.achievements.data.local.db.dao.AchievementsDao
import com.capybara.intuition.achievements.data.local.db.entity.AchievementCategoryEntity
import com.capybara.intuition.achievements.data.local.db.entity.AchievementEntity
import com.capybara.intuition.achievements.data.local.db.entity.ProfileAchievementEntity
import com.capybara.intuition.game.data.local.db.dao.GameModeDao
import com.capybara.intuition.game.data.local.db.dao.GameRoundDao
import com.capybara.intuition.game.data.local.db.dao.MaskDao
import com.capybara.intuition.game.data.local.db.dao.NotebookDao
import com.capybara.intuition.game.data.local.db.dao.NotebookResourceLinkDao
import com.capybara.intuition.game.data.local.db.dao.RendererDao
import com.capybara.intuition.game.data.local.db.dao.ResourceDao
import com.capybara.intuition.game.data.local.db.dao.SessionDao
import com.capybara.intuition.game.data.local.db.dao.topology.TopologyDao
import com.capybara.intuition.game.data.local.db.dao.topology.TopologyFamilyDao
import com.capybara.intuition.game.data.local.db.entity.GameModeEntity
import com.capybara.intuition.game.data.local.db.entity.GameSessionEntity
import com.capybara.intuition.game.data.local.db.entity.MaskEntity
import com.capybara.intuition.game.data.local.db.entity.NotebookEntity
import com.capybara.intuition.game.data.local.db.entity.NotebookResourceLinkEntity
import com.capybara.intuition.game.data.local.db.entity.ObservedGameCardEntity
import com.capybara.intuition.game.data.local.db.entity.ObservedRoundEntity
import com.capybara.intuition.game.data.local.db.entity.RendererEntity
import com.capybara.intuition.game.data.local.db.entity.ResourceEntity
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyCellEntity
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.game.data.local.db.entity.topology.TopologyFamilyRelationEntity
import com.capybara.intuition.app.bootstrap.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.app.bootstrap.installation.data.local.db.entity.InstallationEntity
import com.capybara.intuition.profile.data.local.db.converter.ProfileTypeConverter
import com.capybara.intuition.profile.data.local.db.dao.ProfileDao
import com.capybara.intuition.profile.data.local.db.entity.ProfileEntity
import com.capybara.intuition.statistics.data.local.db.dao.StatisticsDao

@Database(
    entities = [
        InstallationEntity::class,
        GameSessionEntity::class,
        ProfileEntity::class,
        ObservedRoundEntity::class,
        ResourceEntity::class,
        NotebookEntity::class,
        NotebookResourceLinkEntity::class,
        RendererEntity::class,
        MaskEntity::class,
        TopologyCellEntity::class,
        TopologyEntity::class,
        TopologyFamilyEntity::class,
        TopologyFamilyRelationEntity::class,
        ObservedGameCardEntity::class,
        GameModeEntity::class,
        AchievementCategoryEntity::class,
        AchievementEntity::class,
        ProfileAchievementEntity::class

    ],
    version = 21,
    exportSchema = false
)

@TypeConverters(
    ProfileTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun installationDao(): InstallationDao

    abstract fun sessionDao(): SessionDao
    abstract fun gameRoundDao(): GameRoundDao
    abstract fun statisticsDao(): StatisticsDao
    abstract fun profileDao(): ProfileDao

    abstract fun resourceDao(): ResourceDao
    abstract fun notebookDao(): NotebookDao
    abstract fun notebookResourceLinkDao(): NotebookResourceLinkDao
    // =================================================
    // RELATIONAL DIMENSIONS
    // =================================================

    abstract fun rendererDao(): RendererDao

    abstract fun maskDao(): MaskDao
    abstract fun topologyDao(): TopologyDao
    abstract fun topologyFamilyDao(): TopologyFamilyDao
    abstract fun gameModeDao(): GameModeDao

    abstract fun achievementsDao(): AchievementsDao

}