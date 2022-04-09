package github.mewgrammer.taskbook.model.dto;

import github.mewgrammer.taskbook.model.TaskState;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private TaskState status = TaskState.PENDING;
}