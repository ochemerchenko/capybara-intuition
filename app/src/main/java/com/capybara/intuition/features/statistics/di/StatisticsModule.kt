package com.capybara.intuition.features.statistics.di


import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.statistics.data.repository.StatisticsRepository

class StatisticsModule(
    database: AppDatabase
) {

    val statisticsDao = database.statisticsDao()

    val statisticsRepository = StatisticsRepository(
        statisticsDao = statisticsDao
    )

}