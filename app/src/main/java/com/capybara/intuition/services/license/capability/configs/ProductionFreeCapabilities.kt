package com.capybara.intuition.services.license.capability.configs

import com.capybara.intuition.services.license.capability.RuntimeCapabilities

val ProductionFreeCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = false,
        supportsBeforeMode = false,
        supportsHybridMode = false,
        supportsAnalyticTesterProfileRuntime = false
    )