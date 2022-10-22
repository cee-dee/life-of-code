package de.check24.lifeofcode.model.persona

// manages available devs and pms
// required to manage their time during an iteration
data class PersonaPool(
    val persona: List<Persona>
)
