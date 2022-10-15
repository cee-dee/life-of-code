package de.check24.lifeofcode.model.shortcuts

import de.check24.lifeofcode.model.persona.Persona

data class Effect(
    val name: String,
    val timeSpan: TimeSpan,
    val characteristic: Characteristic,
    val persona: Persona
)
