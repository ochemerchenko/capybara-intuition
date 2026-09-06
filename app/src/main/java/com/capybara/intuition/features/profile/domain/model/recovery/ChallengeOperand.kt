package com.capybara.intuition.features.profile.domain.model.recovery

import androidx.annotation.StringRes

data class ChallengeOperand(
    val sourceType: ChallengeValueSourceType,
    var temporalRelationType: TemporalRelation? = null,
    var value: Int = 0,
    @StringRes var descriptionRes: Int = 0
)