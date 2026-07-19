package com.capybara.intuition.profile.tester.components.generator

import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm.calculateBiorhythm
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm.calculateEmotionalFactor
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm.calculateIntellectFactor
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm.calculatePhysicalFactor
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.calculateBaseDecisionTime
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.calculateDailyMotivation
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.calculateMotivationBoost
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.calculateSessionsCount
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.generators.generateSessions
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.generators.generateTimeline
import com.capybara.intuition.profile.tester.model.GeneratedHistoricalDataset
import com.capybara.intuition.profile.tester.model.HistoricalDatasetRequest

class HistoricalDatasetGenerator {

    // ------------------------------------------------------------------------
    // Personality
    // ------------------------------------------------------------------------
    //val competence = 0.3
    //val selfConfidence = 0.5

    // ------------------------------------------------------------------------
    // Biorhythms
    // ------------------------------------------------------------------------
    val physicalPeriod = 23
    val emotionalPeriod = 28
    val intellectPeriod = 33

    // ------------------------------------------------------------------------
    // Influence models
    // ------------------------------------------------------------------------
    val emotionInfluencePeakPosition = 0.5
    val physicalInfluencePeakPosition = 1.0
    val intellectInfluencePeakPosition = 1.0
    val optimalEmotionRange = 0.18
    val optimalPhysicalRange = 0.18
    val optimalIntellectRange = 0.18


    // ------------------------------------------------------------------------
    // Daily motivation
    // ------------------------------------------------------------------------
    val baseDayMotivation = 1.0
    val maxEmotionalDayInfluence = 0.2
    val maxPhysicalDayInfluence = 0.2

    // ------------------------------------------------------------------------
    // External events
    // ------------------------------------------------------------------------
    val analyticPeriod = 30
    //val motivationBoostPerPeriod = 4.0
    val motivationBoostRange = 0.0 .. 1.0
    val dailyMotivationDecay = 0.04


    // ------------------------------------------------------------------------
    // Decision time
    // ------------------------------------------------------------------------
    val baseDecisionTime = 100.0 // 100ms
    val maxEmotionalDecisionTimeInfluence = 3.0
    val maxIntellectDecisionTimeInfluence = 20.0


    // ------------------------------------------------------------------------
    // Session generation
    // ------------------------------------------------------------------------
    //val motivationScale = 30.0
    val mentalEndurance = 0.35
    val baseFatigueGrowth = 0.05


    fun generate(
        request: HistoricalDatasetRequest,
    ): GeneratedHistoricalDataset {

        var timeLines = generateTimeline(
            request.periodDays,
            competence = request.competence,
            selfConfidence = request.selfConfidence
        )

        timeLines = calculateBiorhythm(
            timeLines = timeLines,
            physicalPeriod = physicalPeriod,
            emotionalPeriod = emotionalPeriod,
            intellectPeriod = intellectPeriod
        )

        timeLines = calculateEmotionalFactor(
            timeLines = timeLines,
            optimalEmotionRange = optimalEmotionRange,
            emotionInfluencePeakPosition = emotionInfluencePeakPosition
        )

        timeLines = calculatePhysicalFactor(
            timeLines = timeLines,
            optimalPhysicalRange = optimalPhysicalRange,
            physicalInfluencePeakPosition = physicalInfluencePeakPosition
        )

        timeLines = calculateIntellectFactor(
            timeLines = timeLines,
            optimalIntellectRange = optimalIntellectRange,
            intellectInfluencePeakPosition = intellectInfluencePeakPosition
        )

        timeLines = calculateMotivationBoost(
            timeLines = timeLines,
            period = request.periodDays,
            boostDensity = request.motivationBoostPerPeriod / analyticPeriod,
            motivationBoostRange = motivationBoostRange
        )

        timeLines = calculateDailyMotivation(
            timeLines = timeLines,
            dailyMotivationDecay = dailyMotivationDecay,
            baseDayMotivation = baseDayMotivation,
            maxEmotionalDayInfluence = maxEmotionalDayInfluence,
            maxPhysicalDayInfluence = maxPhysicalDayInfluence,
        )

        timeLines = calculateBaseDecisionTime(
            timeLines = timeLines,
            baseDecisionTime = baseDecisionTime,
            maxEmotionalDecisionTimeInfluence = maxEmotionalDecisionTimeInfluence,
            maxIntellectDecisionTimeInfluence = maxIntellectDecisionTimeInfluence
        )

        timeLines = calculateSessionsCount(
            timeLines = timeLines,
            trainingIntensity = request.trainingIntensity,
            motivationScale = request.motivationScale
        )

        timeLines = generateSessions(
            lastSessionId = request.lastSessionId,
            timeLines = timeLines,
            minLevel = request.minLevel,
            maxLevel = request.maxLevel,
            competence = request.competence,
            selfConfidence = request.selfConfidence,
            mentalEndurance = mentalEndurance,
            baseFatigueGrowth = baseFatigueGrowth
        )

        return GeneratedHistoricalDataset(
            sessions = timeLines.flatMap { it.sessions },
            rounds = timeLines
                .flatMap { it.sessions }
                .flatMap { it.rounds }
        )
    }

}