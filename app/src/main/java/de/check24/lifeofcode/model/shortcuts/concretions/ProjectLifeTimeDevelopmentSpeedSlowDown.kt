package de.check24.lifeofcode.model.shortcuts.concretions

import de.check24.lifeofcode.model.codebase.QualityLevel
import de.check24.lifeofcode.model.shortcuts.TicketDevelopmentTimeModifier
import de.check24.lifeofcode.model.shortcuts.TicketEffect

class ProjectLifeTimeDevelopmentSpeedSlowDown : TicketEffect {
    override val name: String = "Project Lifetime Development Speed Slowdown"

    override fun apply(input: TicketEffect.Input): TicketEffect.Output {
        val newQualityMapLevels = input.gameState.qualityMap.levels.toMutableMap()
        input.areas.areas.forEach { (area, _) ->
            newQualityMapLevels[area]?.let { qualityLevel ->
                val newLevel = qualityLevel.percent * 0.90
                newQualityMapLevels[area] = QualityLevel(newLevel)
            }
        }

        // FIXME: ensure that slow down is taken into account AFTER one iteration
        //  has been completed
        return TicketEffect.Output(
            gameState = input.gameState.copy(
                qualityMap = input.gameState.qualityMap.copy(
                    levels = newQualityMapLevels
                )
            ),
            ticketDevelopmentTimeModifier = TicketDevelopmentTimeModifier(1.0f)
        )
    }
}
