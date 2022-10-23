package de.check24.lifeofcode.model.shortcuts.concretions

import de.check24.lifeofcode.model.shortcuts.TicketDevelopmentTimeModifier
import de.check24.lifeofcode.model.shortcuts.TicketEffect

class IterationDevelopmentSpeedUp : TicketEffect {
    override val name: String = "Iteration Development Speed Up"

    override fun apply(input: TicketEffect.Input): TicketEffect.Output {
        return TicketEffect.Output(
            gameState = input.gameState,
            ticketDevelopmentTimeModifier = TicketDevelopmentTimeModifier(0.5f)
        )
    }
}
