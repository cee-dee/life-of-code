package de.check24.lifeofcode.model.tickets

data class UntilReleaseTimeRequirements(
    // under ideal circumstances: clean code, etc. -- includes developer testing
    val implementationTimeForDeveloper: Hours,
    // under ideal circumstances: testable, test env ready, etc.
    val verificationTime: Hours
)
