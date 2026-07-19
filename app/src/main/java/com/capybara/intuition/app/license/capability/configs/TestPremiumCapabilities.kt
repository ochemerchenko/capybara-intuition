package com.capybara.intuition.license.capability.configs

import com.capybara.intuition.license.capability.RuntimeCapabilities

val TestPremiumCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = true,
        supportsBeforeMode = true,
        supportsHybridMode = true,
        supportsAnalyticTesterProfileRuntime = true
    )