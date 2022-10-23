package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.codebase.Area
import de.check24.lifeofcode.model.codebase.QualityLevel
import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.gamestate.GameState
import de.check24.lifeofcode.model.shared.Percent
import de.check24.lifeofcode.model.shortcuts.ShortCut
import de.check24.lifeofcode.model.shortcuts.concretions.NoCleanCodeShortCut
import de.check24.lifeofcode.model.tickets.*
import de.check24.lifeofcode.model.work.Week
import de.check24.lifeofcode.model.work.WorkLog
import de.check24.lifeofcode.model.work.WorkLogEntry
import org.junit.Assert.*
import org.junit.Test

class TicketImplementationHandlerTest {

    @Test
    fun `processing of first ticket keeps track of performed work hours`() {
        val implementationTimeComputer = ImplementationTimeComputer()
        val sut = TicketImplementationHandler(implementationTimeComputer)

        val actualGameState = sut.handle(
            gameState = initialGameState,
            ticket = firstS1Ticket,
            shortCuts = noShortCuts
        )

        val expectedWorkLogEntry = WorkLogEntry(
            ticket = firstS1Ticket,
            calendarWeek = firstCalendarWeek,
            time = singleDevTime
        )
        val expectedTicketHistoryEntry = HandledTicket(
            ticket = firstS1Ticket,
            implementationStartDate = firstCalendarWeek,
            readyForVerificationDate = secondCalendarWeek
        )
        val expectedGameState =  initialGameState.copy(
            availableDeveloperTime = noTime,
            workLog = initialGameState.workLog.addWorkLogEntry(expectedWorkLogEntry),
            ticketHistory = initialGameState.ticketHistory.addTicketToHistory(expectedTicketHistoryEntry)
        )
        assertEquals(expectedGameState, actualGameState)
    }

    @Test
    fun `processing of first ticket without clean code leads to slow down effect in long run and speeds up iteration`() {
        val implementationTimeComputer = ImplementationTimeComputer()
        val sut = TicketImplementationHandler(implementationTimeComputer)

        val actualGameState = sut.handle(
            gameState = initialGameState,
            ticket = firstS1Ticket,
            shortCuts = noCleanCodeShortCuts
        )

        assertEquals(Percent(90), actualGameState.qualityMap.levels[devAreaS1]!!.percent)

        assertEquals(Hours(20), actualGameState.workLog.entries.first().time)
        assertEquals(Hours(20), actualGameState.availableDeveloperTime)
    }

    private fun WorkLog.addWorkLogEntry(workLogEntry: WorkLogEntry) =
        copy(
            entries = entries + workLogEntry
        )

    private fun TicketHistory.addTicketToHistory(expectedTicketHistoryEntry: HandledTicket) =
        copy(
            handledTickets = handledTickets + expectedTicketHistoryEntry
        )

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
        private val firstS1Ticket = Ticket(
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
        private val noShortCuts = listOf<ShortCut>()
        // FIXME: provide a ShortCut registry later
        private val shortCutNoCleanCode = NoCleanCodeShortCut()
        private val noCleanCodeShortCuts = listOf(
            shortCutNoCleanCode
        )
        private val initialGameState = GameState(
            calendarWeek = firstCalendarWeek,
            availableDeveloperTime = singleDevTime,
            qualityMap = idealQualityMap,
            workLog = emptyWorkLog,
            ticketHistory = emptyTicketHistory
        )
    }
}
