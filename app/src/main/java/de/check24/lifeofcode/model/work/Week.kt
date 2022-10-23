package de.check24.lifeofcode.model.work

@JvmInline
value class Week(
    val week: Int
) {

    operator fun plus(other: Week): Week {
        return Week(week + other.week)
    }
}
