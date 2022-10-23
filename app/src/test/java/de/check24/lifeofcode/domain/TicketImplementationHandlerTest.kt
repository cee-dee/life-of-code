package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.codebase.Area
import de.check24.lifeofcode.model.codebase.QualityLevel
import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.shared.Percent
import de.check24.lifeofcode.model.tickets.*
import de.check24.lifeofcode.model.work.Week
import de.check24.lifeofcode.model.work.WorkLog
import de.check24.lifeofcode.model.work.WorkLogEntry
import org.junit.Assert.*
import org.junit.Test

class TicketImplementationHandlerTest {

    @Test
    fun `processing of first ticket keeps track of performed work hours`() {
        val gameState = GameState(
            calendarWeek = firstCalendarWeek,
            availableDeveloperTime = singleDevTime,
            qualityMap = idealQualityMap,
            workLog = emptyWorkLog,
            ticketHistory = emptyTicketHistory
        )
        val ticket = Ticket(
            summary = "Setup S1 screen",
            description = "Setup S1 screen, including architecture",
            relativeAreas = RelativeAreas(
                hashMapOf(
                    devAreaS1 to Percent(100)
                )
            ),
            hasToBeFinishedByEndOf = Week(week = 1),
            untilReleaseTimeRequirements = UntilReleaseTimeRequirements(
                idealImplementationTimeForDeveloper = singleDevTime,
                idealVerificationTimeForPm = Hours(hours = 0)
            )
        )
        val implementationTimeComputer = ImplementationTimeComputer()
        val sut = TicketImplementationHandler(implementationTimeComputer)

        val actualGameState = sut.handle(
            gameState = gameState,
            ticket = ticket
        )

        val expectedGameState = GameState(
            calendarWeek = firstCalendarWeek,
            availableDeveloperTime = noTime,
            qualityMap = idealQualityMap,
            workLog = WorkLog(
                entries = listOf(
                    WorkLogEntry(
                        ticket = ticket,
                        calendarWeek = firstCalendarWeek,
                        time = singleDevTime
                    )
                )
            ),
            ticketHistory = TicketHistory(
                handledTickets = listOf(
                    HandledTicket(
                        ticket = ticket,
                        implementationStartDate = firstCalendarWeek,
                        readyForVerificationDate = secondCalendarWeek
                    )
                )
            )
        )
        assertEquals(expectedGameState, actualGameState)
    }

    companion object {

        private val singleDevTime = Hours(hours = 40)
        private val noTime = Hours(hours = 0)

        private val firstCalendarWeek = Week(week = 1)
        private val secondCalendarWeek = Week(week = 2)
        private val devAreaS1 = Area(name = "S1")
        private val idealQualityLevels = hashMapOf(
            devAreaS1 to QualityLevel(Percent(100))
        )
        private val idealQualityMap = QualityMap(
            levels = idealQualityLevels
        )
        private val emptyWorkLog = WorkLog(
            entries = listOf()
        )
        private val emptyTicketHistory = TicketHistory(
            handledTickets = listOf()
        )
    }
}
