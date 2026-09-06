package com.capybara.intuition.services.license.capability

data class RuntimeCapabilities(
    val supportsMultiProfileRuntime: Boolean,
    val supportsBeforeMode: Boolean,
    val supportsHybridMode: Boolean,
    val supportsAnalyticTesterProfileRuntime: Boolean
)