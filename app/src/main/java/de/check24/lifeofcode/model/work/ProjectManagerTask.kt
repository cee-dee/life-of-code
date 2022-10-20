package de.check24.lifeofcode.model.work

sealed class ProjectManagerTask(
    override val name: TaskName,
    override val priority: TaskPriority
) : Task {

    object TestFeature : DeveloperTask(
        name = TaskName("Test Feature"),
        priority = TaskPriority(1)
    )
    object TestForRegressions : DeveloperTask(
        name = TaskName("Test for Regressions"),
        priority = TaskPriority(2)
    )
    object CreateNewTickets : DeveloperTask(
        name = TaskName("Create New Tickets"),
        priority = TaskPriority(3)
    )
}
