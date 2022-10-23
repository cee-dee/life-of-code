package de.check24.lifeofcode.model.tickets

import de.check24.lifeofcode.model.work.Week

data class HandledTicket(
    val ticket: Ticket,
    val implementationStartDate: Week,
    val readyForVerificationDate: Week
)
