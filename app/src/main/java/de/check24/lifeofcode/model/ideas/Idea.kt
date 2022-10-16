package de.check24.lifeofcode.model.ideas

import de.check24.lifeofcode.model.tickets.PreparationTimeRequirements

data class Idea(
    val summary: String,
    val description: String,
    val preparationTimeRequirements: PreparationTimeRequirements,
)
