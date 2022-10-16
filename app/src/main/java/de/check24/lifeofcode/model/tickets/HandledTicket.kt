package de.check24.lifeofcode.model.tickets

data class HandledTicket(
    val ticket: Ticket,
    val implementationStartDate: Date,
    val readyForVerificationDate: Date
)
