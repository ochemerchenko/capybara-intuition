package com.capybara.intuition.statistics.presentation.content

import android.content.Context
import java.util.Locale

object MarkdownLoader {

    fun load(
        context: Context,
        baseName: String
    ): String {

        val supportedLanguage =
            when (Locale.getDefault().language) {
                "uk" -> "uk"
                "en" -> "en"
                else -> "uk"
            }

        val localizedPath = "help/$supportedLanguage/$baseName.md"

        val fallbackPath = "help/uk/$baseName.md"

        return try {
            context.assets
                .open(localizedPath)
                .bufferedReader()
                .use { it.readText() }

        } catch (_: Exception) {
            context.assets
                .open(fallbackPath)
                .bufferedReader()
                .use { it.readText() }
        }
    }
}