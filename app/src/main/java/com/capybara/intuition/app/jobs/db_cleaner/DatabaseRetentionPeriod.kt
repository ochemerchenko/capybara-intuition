package com.capybara.intuition.app.jobs.db_cleaner

enum class DatabaseRetentionPeriod {
    DAYS_90,
    DAYS_180,
    DAYS_360,
    FOREVER
}