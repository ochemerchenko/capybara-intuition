package com.capybara.intuition.statistics.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capybara.intuition.app.composition.AppContainer


class StatisticsViewModelFactory(
    private val container: AppContainer,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (
            modelClass.isAssignableFrom(
                StatisticsViewModel::class.java
            )
        ) {
            return StatisticsViewModel(

                statisticsRepository = container.statistics.statisticsRepository


            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}