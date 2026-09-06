package com.capybara.intuition.services.license.capability.configs

import com.capybara.intuition.services.license.capability.RuntimeCapabilities

val TestPremiumCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = true,
        supportsBeforeMode = true,
        supportsHybridMode = true,
        supportsAnalyticTesterProfileRuntime = true
    )