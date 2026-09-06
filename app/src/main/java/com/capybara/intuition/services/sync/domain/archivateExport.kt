package com.capybara.intuition.services.sync.domain

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.io.FileOutputStream
import java.util.zip.GZIPOutputStream

suspend fun archivateExport (
    context: Context,
    installationId: String,
    exportType: String,
    objectKey: String? = null,
    data: JsonObject
){

    val fileName =
        if (objectKey != null) {
            "${installationId}_${exportType}_${objectKey}"
        } else {
            "${installationId}_${exportType}"
        }

    val exportDirectory = File(
        context.filesDir,
        "exports"
    )

    exportDirectory.mkdirs()

    val tmpFile = File(
        exportDirectory,
        "$fileName.tmp"
    )

    val archiveFile = File(
        exportDirectory,
        "$fileName.gz"
    )

    val json = Gson().toJson(data)

    tmpFile.writeText(json)

    GZIPOutputStream(
        FileOutputStream(archiveFile)
    ).use { gzip ->
        tmpFile.inputStream().use { input ->
            input.copyTo(gzip)
        }
    }

    tmpFile.delete()
}