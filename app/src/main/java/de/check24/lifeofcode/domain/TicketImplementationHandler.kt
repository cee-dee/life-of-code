package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.shortcuts.ShortCut
import de.check24.lifeofcode.model.tickets.*
import de.check24.lifeofcode.model.work.Week
import de.check24.lifeofcode.model.work.WorkLogEntry

class TicketImplementationHandler(
    private val implementationTimeComputer: ImplementationTimeComputer
) {

    fun handle(
        gameState: GameState,
        ticket: Ticket,
        shortCuts: List<ShortCut>
    ): GameState {
        val currentCalendarWeek = gameState.calendarWeek
        val qualityMap = gameState.qualityMap
        val availableDeveloperTime = gameState.availableDeveloperTime
        val idealImplementationTimeForDeveloper =
            ticket.untilReleaseTimeRequirements.idealImplementationTimeForDeveloper

        val output = implementationTimeComputer.compute(
            ImplementationTimeComputer.Input(
                gameState = gameState,
                idealImplementationTimeForDeveloper = idealImplementationTimeForDeveloper,
                relativeAreas = ticket.relativeAreas,
                qualityMap = qualityMap,
                shortCuts = shortCuts
            )
        )
        val updatedGameState = output.gameState
        val implementationTimeForDeveloper = output.implementationHours
        // assume that you can split up the work for a ticket amongst all developers
        val spentDeveloperTimeOnTicket = min(
            implementationTimeForDeveloper,
            availableDeveloperTime,
        )
        val newAvailableDeveloperTime = availableDeveloperTime - spentDeveloperTimeOnTicket

        val workLogEntry = WorkLogEntry(
            ticket = ticket,
            calendarWeek = currentCalendarWeek,
            time = spentDeveloperTimeOnTicket
        )
        val newWorkLog = updatedGameState.workLog.copy(
            entries = updatedGameState.workLog.entries + workLogEntry
        )
        val newTicketHistory = updatedGameState.ticketHistory.copy(
            handledTickets = updatedGameState.ticketHistory.handledTickets + HandledTicket(
                ticket = ticket,
                implementationStartDate = currentCalendarWeek,
                // FIXME: handle case where implementation of a ticket takes more than 1 week
                readyForVerificationDate = currentCalendarWeek + Week(1)
            )
        )

        // FIXME: modify QualityMap depending on ShortCuts

        return updatedGameState.copy(
            availableDeveloperTime = newAvailableDeveloperTime,
            workLog = newWorkLog,
            ticketHistory = newTicketHistory
        )
    }
}
