package de.check24.lifeofcode.model.consequences

interface Consequence {

    val name: String
    fun isOccurred(): Boolean
}

/*
Developer exceeded timeline
PM runs out of time
PM cannot create new tickets
Release can not be regression tested
Feature cannot be tested
Feature is malfunctioning
App crashes

--> take priority of Tasks into account to find out what suffers first from too little time
 */
