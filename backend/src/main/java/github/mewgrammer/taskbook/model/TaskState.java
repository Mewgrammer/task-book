package github.mewgrammer.taskbook.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TaskState {
    PENDING,
    IN_PROGRESS,
    FINISHED
}
