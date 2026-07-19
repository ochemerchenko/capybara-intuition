package com.capybara.intuition.statistics.presentation.ui.model

import com.capybara.intuition.statistics.presentation.model.SessionFilter

data class StatisticsSelectionState(

    val pivotMode: PivotMode = PivotMode.LEVEL,
    val selectedLevel: Int = 2,
    val selectedFilter: SessionFilter = SessionFilter.AFTER

)