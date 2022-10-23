package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.tickets.*
import de.check24.lifeofcode.model.work.Week
import de.check24.lifeofcode.model.work.WorkLogEntry

class TicketImplementationHandler {

    fun handle(
        gameState: GameState,
        ticket: Ticket
    ): GameState {
        val currentCalendarWeek = gameState.calendarWeek
        val qualityMap = gameState.qualityMap
        val availableDeveloperTime = gameState.availableDeveloperTime
        val idealImplementationTimeForDeveloper =
            ticket.untilReleaseTimeRequirements.idealImplementationTimeForDeveloper

        val implementationTimeForDeveloper = computeImplementationTimeForDeveloper(
            idealImplementationTimeForDeveloper,
            ticket.relativeAreas,
            qualityMap
        )

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
        val newWorkLog = gameState.workLog.copy(
            entries = gameState.workLog.entries + workLogEntry
        )
        val newTicketHistory = gameState.ticketHistory.copy(
            handledTickets = gameState.ticketHistory.handledTickets + HandledTicket(
                ticket = ticket,
                implementationStartDate = currentCalendarWeek,
                // FIXME: handle case where implementation of a ticket takes more than 1 week
                readyForVerificationDate = currentCalendarWeek + Week(1)
            )
        )

        // FIXME: modify QualityMap depending on ShortCuts

        return gameState.copy(
            availableDeveloperTime = newAvailableDeveloperTime,
            workLog = newWorkLog,
            ticketHistory = newTicketHistory
        )
    }

    private fun computeImplementationTimeForDeveloper(
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
