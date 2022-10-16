package de.check24.lifeofcode.model.tickets

import de.check24.lifeofcode.model.codebase.Area
import de.check24.lifeofcode.model.shared.Percent

data class RelativeAreas(
    // FIXME: ensure 100% in total
    val areas: Map<Area, Percent>
)
