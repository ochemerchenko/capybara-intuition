package com.capybara.intuition.license.capability.configs

import com.capybara.intuition.license.capability.RuntimeCapabilities

val TestFreeCapabilities =
    RuntimeCapabilities(
        supportsMultiProfileRuntime = false,
        supportsBeforeMode = false,
        supportsHybridMode = false,
        supportsAnalyticTesterProfileRuntime = true
    )