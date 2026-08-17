package com.capybara.intuition.app.jobs.export

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
    objectKey: String,
    data: JsonObject
){
    val fileName = "${installationId}_${exportType}_${objectKey}"

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