package de.check24.lifeofcode.model.tickets

import de.check24.lifeofcode.model.work.Week

data class Ticket(
    val summary: String,
    val description: String,
    // set to MAXINT if no deadline?
    val hasToBeFinishedByEndOf: Week,
    val upfrontTimeRequirements: UpfrontTimeRequirements,
    val untilReleaseTimeRequirements: UntilReleaseTimeRequirements
)
