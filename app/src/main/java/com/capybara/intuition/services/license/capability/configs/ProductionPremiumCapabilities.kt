package com.capybara.intuition.services.license.capability.configs

import com.capybara.intuition.services.license.capability.RuntimeCapabilities

val ProductionPremiumCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = true,
        supportsBeforeMode = true,
        supportsHybridMode = true,
        supportsAnalyticTesterProfileRuntime = false
    )