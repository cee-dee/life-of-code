package de.check24.lifeofcode.model.gamestate

import de.check24.lifeofcode.model.codebase.QualityMap
import de.check24.lifeofcode.model.tickets.Hours
import de.check24.lifeofcode.model.tickets.TicketHistory
import de.check24.lifeofcode.model.work.Week
import de.check24.lifeofcode.model.work.WorkLog

data class GameState(
    val calendarWeek: Week,
    val availableDeveloperTime: Hours,
    val qualityMap: QualityMap,
    val workLog: WorkLog,
    val ticketHistory: TicketHistory,
)
