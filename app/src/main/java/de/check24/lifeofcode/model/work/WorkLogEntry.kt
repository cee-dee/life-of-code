package de.check24.lifeofcode.model.work

import de.check24.lifeofcode.model.tickets.Hours
import de.check24.lifeofcode.model.tickets.Ticket

data class WorkLogEntry(
    val ticket: Ticket,
    val calendarWeek: Week,
    val time: Hours
)
