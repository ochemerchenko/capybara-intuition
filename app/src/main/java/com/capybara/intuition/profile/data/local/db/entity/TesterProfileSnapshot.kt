package com.capybara.intuition.profile.data.local.db.entity

data class TesterProfileSnapshot(

    val id: Int,
    val activeNotebookId: Short,
    val activeTopologyId: Short,
    val maskId: Short,
    val rendererId: Short

)