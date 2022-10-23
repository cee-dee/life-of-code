package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.shortcuts.ShortCut
import de.check24.lifeofcode.model.shortcuts.TicketEffect
import de.check24.lifeofcode.model.tickets.Hours
import de.check24.lifeofcode.model.tickets.RelativeAreas
import de.check24.lifeofcode.model.tickets.sum

class ImplementationTimeComputer {

    fun compute(
        input: Input
    ): Output {
        var newGameState = input.gameState
        val hours = input.relativeAreas.areas.keys.map { area ->
            val percent = input.relativeAreas.areas[area]
            val implementationTimeForArea = percent?.let { _percent ->
                val relativeAmountOfImplementationTimeForArea =
                    input.idealImplementationTimeForDeveloper * (_percent.value / 100.0)
                val qualityLevelForArea = input.qualityMap.levels[area]
                val implementationTimeForArea = qualityLevelForArea?.computeEffectOnImplementationTime(
                    relativeAmountOfImplementationTimeForArea
                ) ?: throw IllegalStateException("area $area not found in QualityLevels")

                var implementationTimeForAreaWithShortCuts = implementationTimeForArea
                input.shortCuts.forEach { shortCut ->
                    shortCut.ticketEffects.forEach { ticketEffect ->
                        val output = ticketEffect.apply(
                            TicketEffect.Input(
                                gameState = newGameState,
                                areas = input.relativeAreas
                            )
                        )

                        implementationTimeForAreaWithShortCuts *= output.ticketDevelopmentTimeModifier.factor.toDouble()
                        newGameState = output.gameState
                    }
                }

                implementationTimeForAreaWithShortCuts
            } ?: throw IllegalStateException("mapping for area $area not found, should not happen")

            implementationTimeForArea
        }
            .sum()

        return Output(
            gameState = newGameState,
            implementationHours = hours
        )
    }

    data class Input(
        val gameState: GameState,
        val idealImplementationTimeForDeveloper: Hours,
        val relativeAreas: RelativeAreas,
        val qualityMap: QualityMap,
        val shortCuts: List<ShortCut>
    )

    data class Output(
        val gameState: GameState,
        val implementationHours: Hours
    )
}
