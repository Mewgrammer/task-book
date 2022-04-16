package github.mewgrammer.taskbook.api.model;

import github.mewgrammer.taskbook.model.TaskState;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private TaskState status = TaskState.PENDING;
    private String creator;
    private Instant createdAt;
}