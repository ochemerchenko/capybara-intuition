package com.capybara.intuition.app.composition.modules


import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.statistics.data.repository.StatisticsRepository

class StatisticsModule(
    database: AppDatabase
) {

    val statisticsDao = database.statisticsDao()

    val statisticsRepository = StatisticsRepository(
        statisticsDao = statisticsDao
    )

}