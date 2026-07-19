package com.capybara.intuition.profile.domain.recovery

import com.capybara.intuition.R
import com.capybara.intuition.profile.domain.model.recovery.ArithmeticOperation
import com.capybara.intuition.profile.domain.model.recovery.ChallengeOperand
import com.capybara.intuition.profile.domain.model.recovery.ChallengeTask
import com.capybara.intuition.profile.domain.model.recovery.ChallengeValueSourceType
import com.capybara.intuition.profile.domain.model.recovery.TemporalRelation
import java.time.LocalDate

class RecoveryChallengeGenerator {

    val now = LocalDate.now()

    fun generate(): ChallengeTask {

        val sourceTypes = ChallengeValueSourceType.entries
            .shuffled()
            .take(2)

        val leftOperand = ChallengeOperand(
            sourceType = sourceTypes[0]
        )
        val rightOperand = ChallengeOperand(
            sourceType = sourceTypes[1]
        )

        val operands = listOf(
            leftOperand,
            rightOperand
        )

        operands.forEach { operand ->
            reachTemporalRelationType(operand)
            setOperandValue(operand)
            setOperandDescriptionRes(operand)

        }

        val challengeTask = ChallengeTask(
            operation = ArithmeticOperation.entries.random()
        )

        if (challengeTask.operation == ArithmeticOperation.SUBTRACT) {
            if (leftOperand.value > rightOperand.value) {
                challengeTask.leftOperand = leftOperand
                challengeTask.rightOperand = rightOperand
            } else {
                challengeTask.leftOperand = rightOperand
                challengeTask.rightOperand = leftOperand
            }
            challengeTask.descriptionRes = R.string.settings_recovery_pin_minus_operation
        } else {
            challengeTask.leftOperand = leftOperand
            challengeTask.rightOperand = rightOperand
            challengeTask.descriptionRes = R.string.settings_recovery_pin_plus_operation
        }

        challengeTask.correctAnswer =
            if (challengeTask.operation == ArithmeticOperation.ADD) {
                challengeTask.leftOperand!!.value + challengeTask.rightOperand!!.value
            } else {
                challengeTask.leftOperand!!.value - challengeTask.rightOperand!!.value
            }

        createTaskText(challengeTask)

        return challengeTask
    }

    fun reachTemporalRelationType(
        operand: ChallengeOperand
    ) {
        when (operand.sourceType) {
            ChallengeValueSourceType.RANDOM_NUMBER ->
                operand.temporalRelationType = null
            else ->
                operand.temporalRelationType = TemporalRelation.entries.random()

        }
    }

    fun setOperandValue (
        operand: ChallengeOperand
    ) {
        when (operand.sourceType) {
            ChallengeValueSourceType.YEAR -> {
                setYearValue(operand)
            }
            ChallengeValueSourceType.MONTH -> {
                setMonthValue(operand)
            }
            ChallengeValueSourceType.DAY_OF_MONTH -> {
                setDayOfMonthValue(operand)
            }

            ChallengeValueSourceType.DAY_OF_WEEK -> {
                setDayOfWeekValue(operand)
            }

            ChallengeValueSourceType.RANDOM_NUMBER -> {
                setNumberValue(operand)
            }
        }
    }

    fun setOperandDescriptionRes (
        operand: ChallengeOperand
    ) {
        operand.descriptionRes =
            when (operand.sourceType) {
                ChallengeValueSourceType.YEAR -> {
                    when (operand.temporalRelationType) {
                        TemporalRelation.PREVIOUS -> {
                            R.string.settings_recovery_pin_previous_year
                        }
                        TemporalRelation.CURRENT -> {
                            R.string.settings_recovery_pin_current_year
                        }
                        TemporalRelation.NEXT -> {
                            R.string.settings_recovery_pin_next_year
                        }
                        else -> error(
                            "YEAR requires temporal relation"
                        )
                    }
                }
                ChallengeValueSourceType.MONTH ->
                    when (operand.temporalRelationType) {
                        TemporalRelation.PREVIOUS -> {
                            R.string.settings_recovery_pin_previous_month
                        }
                        TemporalRelation.CURRENT -> {
                            R.string.settings_recovery_pin_current_month
                        }
                        TemporalRelation.NEXT -> {
                            R.string.settings_recovery_pin_next_month
                        }
                        else -> error(
                            "MONTH requires temporal relation"
                        )
                    }
                ChallengeValueSourceType.DAY_OF_MONTH ->
                    when (operand.temporalRelationType) {
                        TemporalRelation.PREVIOUS -> {
                            R.string.settings_recovery_pin_previous_date
                        }
                        TemporalRelation.CURRENT -> {
                            R.string.settings_recovery_pin_current_date
                        }
                        TemporalRelation.NEXT -> {
                            R.string.settings_recovery_pin_next_date
                        }
                        else -> error(
                            "DAY_OF_MONTH requires temporal relation"
                        )
                    }

                ChallengeValueSourceType.DAY_OF_WEEK ->
                    when (operand.temporalRelationType) {
                        TemporalRelation.PREVIOUS -> {
                            R.string.settings_recovery_pin_previous_week_day
                        }
                        TemporalRelation.CURRENT -> {
                            R.string.settings_recovery_pin_current_week_day
                        }
                        TemporalRelation.NEXT -> {
                            R.string.settings_recovery_pin_next_week_day
                        }
                        else -> error(
                            "DAY_OF_WEEK requires temporal relation"
                        )
                    }

                ChallengeValueSourceType.RANDOM_NUMBER -> {
                    R.string.settings_recovery_pin_number
                }

                else -> error(
                    "YEAR requires temporal relation"
                )
            }
    }


    fun setNumberValue(operand: ChallengeOperand) {
        operand.value = (1..31).random()
    }

    fun setYearValue(operand: ChallengeOperand) {
        operand.value =
            when (operand.temporalRelationType) {
                TemporalRelation.PREVIOUS -> {
                    now.year - 1
                }
                TemporalRelation.CURRENT -> {
                    now.year
                }
                TemporalRelation.NEXT -> {
                    now.year + 1
                }
                else -> {
                    error(
                        "YEAR requires temporal relation"
                    )
                }
            }
    }

    fun setMonthValue(operand: ChallengeOperand) {
        operand.value =
            when (operand.temporalRelationType) {
                TemporalRelation.PREVIOUS -> {
                    if (now.monthValue == 1) {
                        12
                    } else {
                        now.monthValue - 1
                    }
                }
                TemporalRelation.CURRENT -> {
                    now.monthValue
                }
                TemporalRelation.NEXT -> {
                    if (now.monthValue == 12) {
                        1
                    } else {
                        now.monthValue + 1
                    }
                }
                else -> error(
                    "MONTH requires temporal relation"
                )
            }
    }

    fun setDayOfMonthValue(operand: ChallengeOperand) {
        operand.value =
            when (operand.temporalRelationType) {
                TemporalRelation.PREVIOUS -> {
                    now.minusDays(1).dayOfMonth
                }
                TemporalRelation.CURRENT -> {
                    now.dayOfMonth
                }
                TemporalRelation.NEXT -> {
                    now.plusDays(1).dayOfMonth
                }
                else -> {
                    error(
                        "DAY_OF_MONTH requires temporal relation"
                    )
                }
            }
    }

    fun setDayOfWeekValue(operand: ChallengeOperand) {
        operand.value =
            when (operand.temporalRelationType) {
                TemporalRelation.PREVIOUS -> {
                    now.minusDays(1).dayOfWeek.value
                }
                TemporalRelation.CURRENT -> {
                    now.dayOfWeek.value
                }
                TemporalRelation.NEXT -> {
                    now.plusDays(1).dayOfWeek.value
                }
                else -> {
                    error(
                        "DAY_OF_WEEK requires temporal relation"
                    )
                }
            }
    }

    fun createTaskText(challengeTask: ChallengeTask) {

    }

}