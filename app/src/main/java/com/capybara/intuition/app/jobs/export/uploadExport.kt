package com.capybara.intuition.app.jobs.export

import android.content.Context
import android.util.Log
import java.io.File
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import androidx.core.net.toUri

suspend fun uploadExport (
    context: Context,
    //storage: StorageReference
){

    val TAG = "EXPORT"

    val exportDirectory = File(
        context.filesDir,
        "exports"
    )

    val files = exportDirectory
        .listFiles { file ->
            file.isFile && file.extension == "gz"
        }
        ?.sortedBy { it.lastModified() }
        ?: return

    for (file in files) {
        try {

            FirebaseStorage
                .getInstance()
                .reference
                .child("exports")
                .child(file.name)
                .putFile(file.toUri())
                .await()
            file.delete()

        } catch (e: Exception) {
            Log.e(
                TAG,
                "Failed to upload ${file.name}",
                e
            )
        }
    }

}