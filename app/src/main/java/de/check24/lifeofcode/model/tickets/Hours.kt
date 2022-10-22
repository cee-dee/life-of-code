package de.check24.lifeofcode.model.tickets

import kotlin.math.roundToInt

@JvmInline
value class Hours(val hours: Int) {

    operator fun plus(other: Hours): Hours {
        return Hours(hours + other.hours)
    }

    operator fun minus(other: Hours): Hours {
        return Hours(hours - other.hours)
    }

    operator fun compareTo(other: Hours): Int {
        return hours - other.hours
    }

    operator fun times(factor: Double): Hours {
        return Hours(
            (hours * factor).roundToInt()
        )
    }
}

fun min(a: Hours, b: Hours): Hours {
    return if (a < b) {
        a
    } else {
        b
    }
}

fun List<Hours>.sum(): Hours {
    return Hours(sumOf { it.hours })
}
