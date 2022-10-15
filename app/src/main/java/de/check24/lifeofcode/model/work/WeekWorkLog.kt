package de.check24.lifeofcode.model.work

data class WeekWorkLog(
    val calendarWeek: Week,
    val entries: List<WorkLogEntry>
)
