package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.bubble

data class BubblePhraseContext (
    val profileName: String? = null,
    val winCount: Int? = null,
    val loseCount: Int? = null,
    val streak: Int? = null,
    val lastBrokenStreak: Int? = null,
    val sessionSuccess: Int? = null,
    val tailOneIn: Int? = null
)

fun String.render(
    context: BubblePhraseContext
): String {

    val values = mapOf(
        "profileName" to (context.profileName ?: ""),
        "winCount" to (context.winCount?.toString() ?: ""),
        "loseCount" to (context.loseCount?.toString() ?: ""),
        "streak" to (context.streak?.toString() ?: ""),
        "lastBrokenStreak" to (context.lastBrokenStreak?.toString() ?: ""),

        "sessionSuccess" to (context.sessionSuccess?.toString() ?: ""),
        "tailOneIn" to (context.tailOneIn?.toString() ?: "")
    )

    return values.entries.fold(this) { text, (key, value) ->
        text.replace("{$key}", value)
    }
}