package de.check24.lifeofcode.model.codebase

import de.check24.lifeofcode.model.shared.Percent
import de.check24.lifeofcode.model.tickets.Hours

data class QualityLevel(
    val percent: Percent
) {

    // FIXME: unclear if this should be moved to some Effect handler
    fun computeEffectOnImplementationTime(hours: Hours): Hours {
        return when (percent.value) {
            in 101..Int.MAX_VALUE -> hours
            in 95..100 -> hours
            in 85..94 -> hours.times(1.2)
            in 65..84 -> hours.times(1.5)
            in 45..64 -> hours.times(2.0)
            in 25..44 -> hours.times(3.0)
            in 0..24 -> hours.times(4.0)
            in Int.MIN_VALUE..-1 -> hours.times(4.0)
            else -> throw IllegalArgumentException("percent ${percent.value} outside of valid range")
        }
    }
}
