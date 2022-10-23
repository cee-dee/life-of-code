package de.check24.lifeofcode.model.shared

import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@JvmInline
value class Percent(
    val value: Int
) {

    operator fun times(value: Double): Percent {
        return Percent(
            max(0.0, min(100.0, (this.value * value))).roundToInt()
        )
    }
}
