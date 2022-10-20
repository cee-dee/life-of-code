package de.check24.lifeofcode.model.work

sealed class DeveloperTask(
    override val name: TaskName,
    override val priority: TaskPriority
) : Task {

    object Develop : DeveloperTask(
        name = TaskName("Develop"),
        priority = TaskPriority(1)
    )
}
