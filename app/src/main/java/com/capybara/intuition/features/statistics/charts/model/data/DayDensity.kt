package com.capybara.intuition.features.statistics.charts.model.data

data class DayDensity(

    val day: String,

    /**
     * Середня щільність дня.
     */
    val dayDensity: Double,

    /**
     * Загальна кількість раундів дня.
     */
    val dayRoundCount: Int,

    /**
     * Загальна кількість сесій дня.
     */
    val daySessionCount: Int,

    /**
     * Середній час прийняття рішення
     * по всіх раундах цього дня (секунди).
     */
    val averageDecisionTime: Double

)