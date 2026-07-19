package com.capybara.intuition.profile.tester.components.generator.ver2.tools.math

import kotlin.random.Random

/**
 * Повертає count різних випадкових чисел із діапазону range.
 *
 * Якщо count >= кількості елементів діапазону,
 * повертає null.
 */
fun randomValues(
    range: IntRange,
    count: Int
): List<Int> {

    val size = range.count()

    if (count >= size) {
        require(count < size) {
            "count ($count) must be less than range size ($size)"
        }
    }

    val values = IntArray(size) { index ->
        range.first + index
    }

    var activeSize = size

    val result = ArrayList<Int>(count)

    repeat(count) {

        val randomIndex =
            Random.Default.nextInt(activeSize)

        result += values[randomIndex]

        values[randomIndex] =
            values[activeSize - 1]

        activeSize--
    }

    return result
}