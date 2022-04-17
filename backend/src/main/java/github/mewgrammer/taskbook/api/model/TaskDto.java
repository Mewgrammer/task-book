package github.mewgrammer.taskbook.api.model;

import github.mewgrammer.taskbook.model.TaskState;

import java.time.Instant;
import java.util.UUID;

public record TaskDto(
    UUID id,
    String title,
    String description,
    TaskState status,
    String creator,
    Instant createdAt
) {}