package de.check24.lifeofcode.model.tickets

data class PreparationTimeRequirements(
    // for simplicity, only take PM time into account
    val ticketCreationTime: Hours
)
