package de.check24.lifeofcode.model.shortcuts.concretions

import de.check24.lifeofcode.model.shortcuts.TicketEffect
import de.check24.lifeofcode.model.shortcuts.ShortCut

class NoCleanCodeShortCut : ShortCut {
    override val name: String = "No Clean Code"
    override val ticketEffects: List<TicketEffect> = listOf(
        ProjectLifeTimeDevelopmentSpeedSlowDown(),
        IterationDevelopmentSpeedUp()
    )
}