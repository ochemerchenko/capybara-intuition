package com.capybara.intuition.profile.domain.model.recovery

import androidx.annotation.StringRes

data class ChallengeTask (
    val operation: ArithmeticOperation,
    var leftOperand: ChallengeOperand? = null,
    var rightOperand: ChallengeOperand? = null,
    var correctAnswer: Int? = null,
    @StringRes var descriptionRes: Int = 0
)