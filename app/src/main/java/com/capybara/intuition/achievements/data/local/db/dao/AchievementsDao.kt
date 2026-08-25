package com.capybara.intuition.achievements.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.capybara.intuition.achievements.domain.model.Achievement
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementsDao {

    @Query("""
        SELECT
            a.name,
            pa.value,
            pa.new
        FROM profile_achievements pa
        INNER JOIN profiles p
            ON p.id = pa.profileId
        INNER JOIN achievements a
            ON a.id = pa.achievementId
        INNER JOIN achievement_categories ac
            ON ac.id = a.categoryId
        WHERE p.active = 1
          AND ac.value = 'records'
        ORDER BY a.`order`
    """)
    fun getRecords(): Flow<List<Achievement>>


    @Query("""
        SELECT
            a.name,
            pa.value,
            pa.new
        FROM profile_achievements pa
        INNER JOIN profiles p
            ON p.id = pa.profileId
        INNER JOIN achievements a
            ON a.id = pa.achievementId
        INNER JOIN achievement_categories ac
            ON ac.id = a.categoryId
        WHERE p.active = 1
          AND ac.value = 'regularities'
        ORDER BY a.`order`
    """)
    fun getRegularities(): Flow<List<Achievement>>


    @Query("""
        SELECT
            a.name,
            pa.value,
            pa.new
        FROM profile_achievements pa
        INNER JOIN profiles p
            ON p.id = pa.profileId
        INNER JOIN achievements a
            ON a.id = pa.achievementId
        INNER JOIN achievement_categories ac
            ON ac.id = a.categoryId
        WHERE p.active = 1
          AND ac.value = 'skills'
        ORDER BY a.`order`
    """)
    fun getSkills(): Flow<List<Achievement>>


    @Query("""
        SELECT
            a.name,
            pa.value,
            pa.new
        FROM profile_achievements pa
        INNER JOIN profiles p
            ON p.id = pa.profileId
        INNER JOIN achievements a
            ON a.id = pa.achievementId
        INNER JOIN achievement_categories ac
            ON ac.id = a.categoryId
        WHERE p.active = 1
          AND ac.value = 'milestones'
        ORDER BY a.`order`
    """)
    fun getMilestones(): Flow<List<Achievement>>

    @Query("""
        UPDATE profile_achievements
        SET new = 0
        WHERE profileId IN (
            SELECT id
            FROM profiles
            WHERE active = 1
        )
    """)
    suspend fun markAllAsSeen()

}