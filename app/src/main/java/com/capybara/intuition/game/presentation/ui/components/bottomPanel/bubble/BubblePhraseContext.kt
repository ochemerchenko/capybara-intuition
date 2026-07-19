package com.capybara.intuition.game.presentation.ui.components.bottomPanel.bubble

data class BubblePhraseContext (
    val profileName: String? = null,
    val roundResult: String? = null,
    val winCount: Int? = null,
    val loseCount: Int? = null,
    val streak: Int? = null,
)

fun String.render(
    context: BubblePhraseContext
): String {
    val values = mapOf(
        "profileName" to (context.profileName ?: ""),
        "roundResult" to (context.roundResult ?: ""),
        "winCount" to (context.winCount?.toString() ?: ""),
        "loseCount" to (context.loseCount?.toString() ?: ""),
        "streak" to (context.streak?.toString() ?: "")
    )

    return values.entries.fold(this) { text, (key, value) ->
        text.replace("{$key}", value)
    }
}