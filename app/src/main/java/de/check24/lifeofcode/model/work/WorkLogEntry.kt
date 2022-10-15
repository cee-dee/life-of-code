package de.check24.lifeofcode.model.work

import de.check24.lifeofcode.model.tickets.Hours
import de.check24.lifeofcode.model.persona.Persona

// intended to compute the overall work and in order to decide what has to be dropped
data class WorkLogEntry(
    val name: TaskName,
    val time: Hours,
    val persona: Persona
)
