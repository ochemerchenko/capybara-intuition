package com.capybara.intuition.game.data.mapper

import com.capybara.intuition.game.domain.model.RoundResult

object RoundResultMapper {

    fun fromBoolean(result: Boolean): RoundResult =
        if (result) {
            RoundResult.WIN
        } else {
            RoundResult.LOSE
        }

    fun toBoolean(result: RoundResult): Boolean =
        when (result) {
            RoundResult.WIN -> true
            RoundResult.LOSE -> false
        }

    fun fromString(result: String): Boolean =
        when (result) {
            "WIN" -> true
            "LOSE" -> false
            else -> error("Unknown RoundResult: $result")
        }
}