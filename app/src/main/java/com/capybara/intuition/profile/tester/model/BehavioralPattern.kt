package com.capybara.intuition.profile.tester.model

enum class BehavioralPattern {

    /**
     * Mean density remains unchanged
     * throughout the entire period.
     */
    STABLE,

    /**
     * Mean density gradually increases
     * throughout the period.
     */
    SKILLED,

    /**
     * Mean density gradually decreases
     * throughout the period.
     */
    ANTI_SKILLED
}