package com.capybara.intuition.services.bootstrap.model

enum class BootstrapState {
    NOT_STARTED,
    LICENSE_READY,
    RESOURCES_READY,
    NOTEBOOKS_READY,
    NOTEBOOK_RESOURCE_LINKS_READY,
    RENDERERS_READY,
    MASKS_READY,
    TOPOLOGIES_READY,
    GAME_MODES_READY,
    PROFILES_READY,
    COMPLETED,
    FAILED
}