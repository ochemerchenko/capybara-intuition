package com.capybara.intuition.license.capability.configs

import com.capybara.intuition.license.capability.RuntimeCapabilities

val ProductionFreeCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = false,
        supportsBeforeMode = false,
        supportsHybridMode = false,
        supportsAnalyticTesterProfileRuntime = false
    )