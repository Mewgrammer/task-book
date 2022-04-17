package github.mewgrammer.taskbook.api.model;

import github.mewgrammer.taskbook.model.TaskState;

public record CreateTaskDto(String title, String description, TaskState status) {

}