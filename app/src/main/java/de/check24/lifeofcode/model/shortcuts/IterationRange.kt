package de.check24.lifeofcode.model.shortcuts

import de.check24.lifeofcode.model.work.Week

sealed class IterationRange(
    open val startCalendarWeek: Week
) {

    data class EndsOnTicketFinish(
        override val startCalendarWeek: Week
    ) : IterationRange(
        startCalendarWeek
    )

    data class NeverEnds(
        override val startCalendarWeek: Week
    ) : IterationRange(
        startCalendarWeek = startCalendarWeek
    )
}
