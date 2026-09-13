package com.capybara.intuition.core.tools.markdown

import android.content.Context
import java.io.IOException
import java.util.Locale

object MarkdownAssetLoader {

    /**
     * Loads a localized markdown file from assets.
     * 
     * @param context Android context to access assets.
     * @param directory The base directory in assets (e.g., "help", "info", "onboarding").
     * @param fileName The name of the file (e.g., "about.md").
     * @param fallbackLanguage The language to use as fallback if current locale file is not found.
     * @return The content of the markdown file.
     */
    fun load(
        context: Context,
        directory: String,
        fileName: String,
        fallbackLanguage: String = "en"
    ): String {
        val currentLanguage = Locale.getDefault().language
        
        // Try current language first
        val primaryPath = "$directory/$currentLanguage/$fileName"
        
        // If current is not fallback, prepare fallback path
        val fallbackPath = "$directory/$fallbackLanguage/$fileName"

        return try {
            context.assets.open(primaryPath).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            if (currentLanguage != fallbackLanguage) {
                try {
                    context.assets.open(fallbackPath).bufferedReader().use { it.readText() }
                } catch (e2: IOException) {
                    "" // Even fallback failed
                }
            } else {
                "" // Current was already fallback and it failed
            }
        }
    }
}
