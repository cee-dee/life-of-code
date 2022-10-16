package de.check24.lifeofcode.model.persona

import de.check24.lifeofcode.model.tickets.Hours

sealed class Persona(
    val name: String,
    val availableTimePerIteration: Hours
) {

    object Developer : Persona(
        name = "Developer",
        // 1/8 of time for meetings, etc.
        availableTimePerIteration = Hours(35)
    )

    object ProductManager: Persona(
        name = "Product Manager",
        // 1/2 of time for screening the market, etc.
        availableTimePerIteration = Hours(20)
    )
}
