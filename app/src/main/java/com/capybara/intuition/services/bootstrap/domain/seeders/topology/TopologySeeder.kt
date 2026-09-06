package com.capybara.intuition.services.bootstrap.domain.seeders.topology

import android.content.Context
import android.util.Log
import com.capybara.intuition.features.game.data.local.db.dao.topology.TopologyDao
import com.capybara.intuition.features.game.data.local.db.dao.topology.TopologyFamilyDao
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyCellEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyRelationEntity
import com.google.gson.Gson

class TopologySeeder(
    private val context: Context,
    private val topologyDao: TopologyDao,
    private val topologyFamilyDao: TopologyFamilyDao
) {

    suspend fun seedIfEmpty() {

        val existingTopologies =
            topologyDao.getTopologiesCount()

        if (existingTopologies > 0) {
            return
        }

        seedTopologies()
        seedFamilies()
        seedRelations()
    }

    private suspend fun seedTopologies() {

        Log.d("BOOTLOADER", "seedTopologies() start")

        val json =
            context
                .assets
                .open("bootstrap/topologies.json")
                .bufferedReader()
                .use { it.readText() }

        Log.d("BOOTLOADER", "topologies json loaded")

        val bootstrapTopologies =
            Gson().fromJson(
                json,
                Array<BootstrapTopology>::class.java
            ).toList()

        Log.d("BOOTLOADER", "topologies json loaded 2")

        val topologyEntities =
            bootstrapTopologies.map { topology ->

                try {
                    TopologyEntity(
                        id = topology.id,
                        code = topology.code,
                        spaceWidth = topology.spaceWidth,
                        spaceHeight = topology.spaceHeight,
                        occupancyCapacity = topology.occupancyCapacity,
                        horizontalAlignment = topology.projection.horizontalAlignment,
                        verticalAlignment = topology.projection.verticalAlignment,
                        scaleMode = topology.projection.scaleMode
                    )
                } catch (e: Exception) {
                    Log.e(
                        "BOOTLOADER",
                        "Failed for topology code=${topology.code}",
                        e
                    )
                    throw e
                }


            }

        Log.d("BOOTLOADER", "topologies json loaded 3")

        val cellEntities =
            bootstrapTopologies.flatMap { topology ->

                topology.cells.map { cell ->

                    TopologyCellEntity(
                        topologyId = topology.id,
                        index = cell.index,
                        x = cell.x,
                        y = cell.y,
                        width = cell.width,
                        height = cell.height,
                        blocked = cell.blocked
                    )
                }
            }
        Log.d("BOOTLOADER", "inserting topologies")
        topologyDao.insertTopologies(
            topologyEntities
        )
        Log.d("BOOTLOADER", "topologies inserted")
        topologyDao.insertTopologyCells(
            cellEntities
        )
    }

    private suspend fun seedFamilies() {

        val json =
            context
                .assets
                .open("bootstrap/topology_families.json")
                .bufferedReader()
                .use { it.readText() }

        val families =
            Gson().fromJson(
                json,
                Array<TopologyFamilyEntity>::class.java
            ).toList()

        topologyFamilyDao.insertFamilies(
            families
        )
    }

    private suspend fun seedRelations() {

        val json =
            context
                .assets
                .open("bootstrap/topology_family_relations.json")
                .bufferedReader()
                .use { it.readText() }

        val relations =
            Gson().fromJson(
                json,
                Array<BootstrapTopologyFamilyRelation>::class.java
            ).toList()

        Log.d("BOOTLOADER", "seedFamilies: relations = $relations")

        topologyFamilyDao.insertRelations(
            relations.map {
                TopologyFamilyRelationEntity(
                    familyId = it.familyId,
                    topologyId = it.topologyId
                )
            }
        )
    }
}