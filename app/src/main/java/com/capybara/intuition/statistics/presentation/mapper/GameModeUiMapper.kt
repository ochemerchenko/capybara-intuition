package com.capybara.intuition.statistics.presentation.mapper

import androidx.annotation.StringRes
import com.capybara.intuition.R
import com.capybara.intuition.statistics.presentation.model.SessionFilter

@StringRes
fun sessionFilterLabelRes(filter: SessionFilter): Int =
    when (filter) {
        SessionFilter.BEFORE -> R.string.statistics_mode_before
        SessionFilter.AFTER -> R.string.statistics_mode_after
        SessionFilter.HYBRID -> R.string.statistics_mode_hybrid
        SessionFilter.ALL -> R.string.statistics_all_sessions
    }
