package de.check24.lifeofcode.model.shortcuts

import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.tickets.RelativeAreas

interface TicketEffect {
    val name: String

    fun apply(input: Input): Output

    // FIXME: think about slimming down data to operate on
    data class Input(
        val gameState: GameState,
        val areas: RelativeAreas
    )

    data class Output(
        val gameState: GameState,
        val ticketDevelopmentTimeModifier: TicketDevelopmentTimeModifier
    )
}
