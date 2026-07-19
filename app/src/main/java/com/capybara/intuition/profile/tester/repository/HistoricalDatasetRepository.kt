package com.capybara.intuition.profile.tester.repository

import androidx.room.withTransaction
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.game.data.local.db.entity.GameSessionEntity
import com.capybara.intuition.game.data.local.db.entity.ObservedRoundEntity
import com.capybara.intuition.game.data.mapper.GameModeMapper
import com.capybara.intuition.game.data.mapper.RoundResultMapper
import com.capybara.intuition.game.data.mapper.RoundTypeMapper
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.game.domain.model.RoundType
import com.capybara.intuition.profile.tester.model.GeneratedHistoricalDataset

class HistoricalDatasetRepository(
    private val database: AppDatabase
) {

    private val sessionDao = database.sessionDao()
    private val roundDao = database.gameRoundDao()
    private val profileDao = database.profileDao()


    suspend fun save(
        dataset: GeneratedHistoricalDataset
    ): Boolean {

        return try {

            val tester = profileDao.getTesterSnapshot()
                ?: return false

            val sessions = dataset.sessions.map { generated ->

                GameSessionEntity(
                    id = generated.id,
                    profileId = tester.id,
                    mode = GameModeMapper.toId(
                        GameMode.valueOf(generated.mode)
                    ),
                    level = generated.level,
                    notebookId = tester.activeNotebookId,
                    topologyId = tester.activeTopologyId,
                    maskId = tester.maskId,
                    rendererId = tester.rendererId,
                    startedAt = generated.startedAt,
                    finishedAt = generated.finishedAt
                )
            }

            val rounds = dataset.rounds.map { generated ->

                ObservedRoundEntity(
                    sessionId = generated.sessionId,
                    roundType = RoundTypeMapper.toId(
                        RoundType.valueOf(generated.mode)
                    ),
                    level = 0,
                    startedAt = generated.startedAt,
                    userSelectedAt = generated.userSelectedAt,
                    finishedAt = generated.finishedAt,
                    result = RoundResultMapper.fromString(
                        generated.result
                    )
                )
            }

            database.withTransaction {

                sessionDao.insertSessions(sessions)
                roundDao.insertRounds(rounds)
            }

            true

        } catch (_: Exception) {

            false
        }
    }


    suspend fun clear(): Boolean {

        return try {

            database.withTransaction {

                sessionDao.deleteTesterDataset()
            }

            true

        } catch (_: Exception) {

            false
        }
    }

    suspend fun getLastSessionId(): Long {
        return sessionDao.getLastSessionId()
    }

}
