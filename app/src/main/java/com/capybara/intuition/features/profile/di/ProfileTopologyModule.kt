package com.capybara.intuition.features.profile.di

import com.capybara.intuition.app.AppDatabase

class ProfileTopologyModule (
    database: AppDatabase
) {
    val topologyFamilyDao = database.topologyFamilyDao()
    val topologyDao = database.topologyDao()
}