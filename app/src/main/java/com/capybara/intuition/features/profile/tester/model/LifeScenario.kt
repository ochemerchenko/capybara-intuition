package com.capybara.intuition.features.profile.tester.model

enum class LifeScenario {

    /**
     * Sessions are distributed
     * approximately evenly across the period.
     */
    REGULAR,

    /**
     * Sessions are concentrated
     * near weekends.
     */
    WEEKEND_ONLY,

    /**
     * Long inactive intervals
     * are inserted into the period.
     */
    VACATIONS,

    /**
     * Short bursts of intensive activity
     * separated by inactive intervals.
     */
    BURSTS,

    /**
     * No visible calendar pattern.
     */
    CHAOTIC
}