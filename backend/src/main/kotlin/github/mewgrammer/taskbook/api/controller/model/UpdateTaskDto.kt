package github.mewgrammer.taskbook.api.controller.model

import github.mewgrammer.taskbook.model.TaskState

class UpdateTaskDto(
    val title: String,
    val description: String,
    val state: TaskState
)