package de.check24.lifeofcode.domain

import de.check24.lifeofcode.model.shortcuts.TicketEffect
import de.check24.lifeofcode.model.shortcuts.ShortCut

interface EffectFactory {
    fun computeEffect(shortCut: ShortCut): List<TicketEffect>
}

/*
for a ShortCut can be multiple Effects
create a registry for EffectFactories
for a given ShortCut call all such factories that can handle the ShortCut

apply immediate effects to the current iteration and other effects for the upcoming iterations
 */

