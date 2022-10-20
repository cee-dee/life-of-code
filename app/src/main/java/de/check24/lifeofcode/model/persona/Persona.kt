package de.check24.lifeofcode.model.persona

import de.check24.lifeofcode.model.tickets.Hours

sealed class Persona(
    val name: String,
    val availableTimePerIteration: Hours
) {

    object Developer : Persona(
        name = "Developer",
        availableTimePerIteration = Hours(40)
    )

    object ProductManager: Persona(
        name = "Product Manager",
        availableTimePerIteration = Hours(20) // time for screening the market, etc.
    )
}
