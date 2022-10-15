package de.check24.lifeofcode.model.shortcuts

typealias Factor = Float

sealed class Characteristic(
    val name: String
) {

    data class ImplementationTime(
        val factor: Factor
    ) : Characteristic(
        name = "Implementation Time"
    )
}
