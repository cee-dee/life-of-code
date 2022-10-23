package de.check24.lifeofcode.model.shortcuts.concretions

import de.check24.lifeofcode.model.shortcuts.IterationRange
import de.check24.lifeofcode.model.shortcuts.TimeSpan
import de.check24.lifeofcode.model.work.Week

class ProjectLifeTimeTimeSpan(
    startCalendarWeek: Week
) : TimeSpan {
    override val name: String = "project lifetime"
    override val range: IterationRange = IterationRange.NeverEnds(
        startCalendarWeek = startCalendarWeek
    )
}
