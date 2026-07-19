package com.capybara.intuition.app.composition.modules.profile

import com.capybara.intuition.app.AppDatabase

class ProfileTopologyModule (
    database: AppDatabase
) {
    val topologyFamilyDao = database.topologyFamilyDao()
    val topologyDao = database.topologyDao()
}