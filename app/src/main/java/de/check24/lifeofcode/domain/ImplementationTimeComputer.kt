package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.tickets.Hours
import de.check24.lifeofcode.model.tickets.RelativeAreas
import de.check24.lifeofcode.model.tickets.sum

class ImplementationTimeComputer {

    fun compute(
        idealImplementationTimeForDeveloper: Hours,
        relativeAreas: RelativeAreas,
        qualityMap: QualityMap
    ): Hours {
        val hours = relativeAreas.areas.keys.map { area ->
            val percent = relativeAreas.areas[area]
            val implementationTimeForArea = percent?.let { _percent ->
                val relativeAmountOfImplementationTimeForArea =
                    idealImplementationTimeForDeveloper * (_percent.value / 100.0)
                val qualityLevelForArea = qualityMap.levels[area]
                val implementationTimeForArea = qualityLevelForArea?.computeEffectOnImplementationTime(
                    relativeAmountOfImplementationTimeForArea
                ) ?: throw IllegalStateException("area $area not found in QualityLevels")

                implementationTimeForArea
            } ?: throw IllegalStateException("mapping for area $area not found, should not happen")

            implementationTimeForArea
        }
            .sum()

        return hours
    }
}
